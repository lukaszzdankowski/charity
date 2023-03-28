package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.DonationService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final DonationService donationService;

    @GetMapping("/home")
    public String adminHome() {
        return "admin/home-admin";
    }

    @GetMapping("/donation-list")
    public String showDonations(Model model) {
        List<Donation> donations = donationService.listAllDonations();
        model.addAttribute("donations", donations);
        return "admin/donation-list";
    }

    @GetMapping("/donation-archived/{donationId}")
    public String setDonationAsArchived(@PathVariable String donationId) {
        donationService.setDonationAsArchived(donationId);
        return "redirect:/admin/donation-list";
    }
}
