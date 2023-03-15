package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {
    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "guest/register";
    }

    @PostMapping("/register")
    public String addNewUser(User user) throws MessagingException {
        userService.saveNewUser(user);
        return "guest/register-sent";
    }

    @GetMapping("/register-token/{tokenString}")
    public String registerToken(@PathVariable String tokenString) {
        String redirect = userService.setUserActive(tokenString);
        return redirect;
    }

    @GetMapping("/password-forgot")
    public String resetPassword() {
        return "guest/password-forgot-form";
    }

    @PostMapping("/password-forgot")
    public String resetPasswordSend(@RequestParam String email) throws MessagingException {
        String redirect = userService.resetPasswordSend(email);
        return redirect;
    }

    @GetMapping("/password-token/{tokenString}")
    public String passwordToken(@PathVariable String tokenString, Model model) {
        String string = userService.resetPasswordReceive(tokenString);
        if (!string.contains("@")) {
            return string;
        } else {
            model.addAttribute("email", string);
            return "guest/password-reset-form";
        }
    }

    @PostMapping("/password-reset")
    public String passwordReset(@RequestParam String email,
                                @RequestParam String password) {
        userService.resetPassword(email, password);
        return "guest/password-reset-confirmed";
    }
}
