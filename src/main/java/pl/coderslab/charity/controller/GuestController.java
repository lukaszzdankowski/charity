package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.TokenService;
import pl.coderslab.charity.service.UserService;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {
    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping("/not-active")//nie używana
    public String notActiveUserLogin() {
        return "guest/user-not-active";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register-form";
    }

    @PostMapping("/register")
    public String addNewUser(User user) throws MessagingException {
        userService.saveNewUser(user);
        return "guest/register-sent";
    }

    @GetMapping("/register-token/{tokenString}")
    public String registerToken(@PathVariable String tokenString) {
        int redirectCase = userService.setUserActive(tokenString);
        switch (redirectCase) {
            case 1:
                return "guest/token-expired";
            case 2:
                return "guest/register-confirmed";
            default:
                return "guest/token-not-found";
        }
    }

    @GetMapping("/password-forgot")
    public String resetPassword() {
        return "guest/password-forgot-form";
    }

    @PostMapping("/password-forgot")
    public String resetPasswordSend(@RequestParam String email) throws MessagingException {
        boolean redirectCase = userService.resetPasswordSend(email);
        if (redirectCase) {
            return "guest/password-forgot-sent";
        } else {
            return "guest/password-forgot-no-email";
        }
    }

    @GetMapping("/password-token/{tokenString}")
    public String passwordToken(@PathVariable String tokenString, Model model) {
        int redirectCase = userService.resetPasswordReceive(tokenString);
        switch (redirectCase) {
            case 1:
                return "guest/token-expired";
            case 2:
                String email = tokenService.retrieveEmailAndDeleteToken(tokenString);
                model.addAttribute("email", email);
                return "guest/password-reset-form";
            default:
                return "guest/token-not-found";
        }
    }

    @PostMapping("/password-reset")
    public String passwordReset(@RequestParam String email,
                                @RequestParam String password) {
        userService.resetPassword(email, password);
        return "guest/password-reset-confirmed";
    }
}
