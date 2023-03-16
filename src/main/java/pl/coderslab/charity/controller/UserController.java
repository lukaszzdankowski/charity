package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import javax.mail.MessagingException;
import java.security.Principal;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    @GetMapping("/home")
    public String userHome() {
        return "user/home-user";
    }

    @GetMapping("/donation/form")
    public String formDisplay(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("categoryList", categoryService.getCategories());
        model.addAttribute("institutionList", institutionService.getInstitutions());
        return "user/donation-form";
    }

    @PostMapping("/donation/form")
    public String saveDonation(Principal principal, Donation donation) throws MessagingException {
        User user = userService.getUserByEmail(principal.getName());
        donationService.saveAndSendMessage(user, donation);
        return "user/donation-confirmation";
    }
}
