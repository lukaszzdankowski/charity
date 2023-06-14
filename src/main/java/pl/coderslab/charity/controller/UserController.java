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
import java.util.List;

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

    @GetMapping("/donation-add")
    public String formDisplay(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("categoryList", categoryService.getActiveCategories());
        model.addAttribute("institutionList", institutionService.getActiveInstitutions());
        return "user/donation-form";
    }

    @PostMapping("/donation-add")
    public String saveDonation(Principal principal, Donation donation) throws MessagingException {
        User user = userService.getUserByEmail(principal.getName());
        donationService.saveAndSendMessage(user, donation);
        return "user/donation-confirmation";
    }

    @GetMapping("/donation-list")
    public String showDonations(Principal principal, Model model) {
        User user = userService.getUserByEmail(principal.getName());
        List<Donation> donations = donationService.donationListForUser(user);
        model.addAttribute("donations", donations);
        return "user/donation-list";
    }
}
