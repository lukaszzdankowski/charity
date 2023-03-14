package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.EmailSenderService;
import pl.coderslab.charity.service.UserService;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String saveUser(User user) throws MessagingException {
        user.getRoles().add(roleRepository.getById(2L));//czy można w konstruktorze-lepiej w servisie
        userService.saveWithHash(user);
        emailSenderService.sendRegistration(user);
        return "sprawdź skrzynkę";
    }

    @GetMapping("/password-forgot")
    public String remindPassword() {
        return "reminder";
    }

    @PostMapping("/password-forgot")
    @ResponseBody
    public String remindPasswordSend(@RequestParam String email) throws MessagingException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return "Nie odnaleziono takiego email w bazie";
        }
        emailSenderService.sendReminder(user);
        return "Wiadomość e-mail została wysłana. Sprawdź skrzynkę";
    }

    @PostMapping("/password-reset")
    @ResponseBody
    public String passwordReset(@RequestParam String email,
                                @RequestParam String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        user.setPassword(password);
        userService.saveWithHash(user);
        return "password changed";
    }

    @GetMapping("/token/{tokenString}")
    public String tokenHandler(@PathVariable String tokenString, Model model) {
        Token token = tokenRepository.findById(tokenString).orElse(null);
        if (token == null) {
            return "token-not-found";
        }
        User user = token.getUser();
        switch (token.getPurpose()) {
            case "registration":
                user.setActive(true);
                userService.save(user);//czy inaczej?
                tokenRepository.delete(token);
                return "registartion-confirmed";
            case "passwordReset":
                tokenRepository.delete(token);
                model.addAttribute("email", user.getEmail());
                return "paswordResetForm";
            default:
                return "no-action-for-token";
        }
    }
}
