package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.EmailSenderService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final EmailSenderService emailSenderService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutionList", institutionService.getInstitutions());
        model.addAttribute("quantityTotal", donationService.countTotalQuantity());
        model.addAttribute("donationsTotal", donationService.countTotalDonations());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/router")
    public String loginRedirect(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {//czy to nie problem, że ma dwie role
            return "redirect:admin/home";
        }
        return "redirect:user/home";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(User user) throws MessagingException {
        user.getRoles().add(roleRepository.getById(2L));
        emailSenderService.sendRegistration(user.getEmail());
        userService.saveWithHash(user);
        return "redirect: ";
    }

    @GetMapping("/registry-confirmation/{email}/{token}")
    @ResponseBody
    private String registryConfirmation(@PathVariable String email,
                                        @PathVariable String token) {
        if (BCrypt.checkpw(email, token)) {
            User user = userRepository.findByEmail(email).orElse(null);
            user.setActive(true);
            userService.save(user);//czy inaczej?
            return "confirmed";
        }
        return "not-confirmed";
    }

    @GetMapping("/reminder")
    public String remindPassword() {
        return "reminder";
    }

    @PostMapping("/reminder")
    @ResponseBody
    public String remindPasswordSend(@RequestParam String email) throws MessagingException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return "Nie odnaleziono takiego email w bazie";
        }
        emailSenderService.sendReminder(email);
        return "Wiadomość e-mail została wysłana. Sprawdź skrzynkę";
    }

    @GetMapping("/password-reset/{email}/{token}")
    private String passwordResetForm(@PathVariable String email,
                                     @PathVariable String token,
                                     Model model) {
        if (BCrypt.checkpw(email, token)) {
            model.addAttribute("email", email);
            return "paswordResetForm";
        }
        return "badToken";
    }

    @PostMapping("/password-reset")
    @ResponseBody
    private String passwordReset(@RequestParam String email,
                                 @RequestParam String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        user.setPassword(password);
        userService.saveWithHash(user);
        return "password changed";
    }
}
