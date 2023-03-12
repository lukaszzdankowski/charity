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

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(User user) throws MessagingException {
        user.getRoles().add(roleRepository.getById(2L));
        emailSenderService.sendRegistration(user.getEmail());
        userService.save(user);
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

    @GetMapping("/default")
    public String loginRedirect(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {//czy to nie problem, że ma dwie role
            return "redirect:admin/home";
        }
        return "redirect:user/home";
    }
}
