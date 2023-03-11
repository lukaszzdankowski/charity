package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class DonationController {
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @GetMapping("/donation/form")
    public String formDisplay(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("categoryList", categoryService.getCategories());
        model.addAttribute("institutionList", institutionService.getInstitutions());
        return "user/donation/form";
    }

    @PostMapping("/donation/form")
    public String saveDonation(Donation donation) {
        donationService.save(donation);
        return "user/donation/confirmation";
    }

}
