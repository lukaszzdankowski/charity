package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("institutionList", institutionService.getActiveInstitutions());
        model.addAttribute("quantityTotal", donationService.countTotalQuantity());
        model.addAttribute("donationsTotal", donationService.countTotalDonations());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping("/no-page")//nie używana
    public String noPage() {
        return "no-such-page";
    }
}
