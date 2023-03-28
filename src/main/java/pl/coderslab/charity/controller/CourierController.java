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
@RequestMapping("/courier")
@RequiredArgsConstructor
public class CourierController {
    private final DonationService donationService;

    @GetMapping("/home")
    public String adminHome() {
        return "courier/home-courier";
    }

    @GetMapping("/donation-list")
    public String showDonations(Model model) {
        List<Donation> donations = donationService.donationListForCourier();
        model.addAttribute("donations", donations);
        return "courier/donation-list";
    }

    @GetMapping("/donation-received/{donationId}")
    public String setDonationAsReceived(@PathVariable String donationId) {
        donationService.setDonationAsReceived(donationId);
        return "redirect:/courier/donation-list";
    }

    @GetMapping("/donation-delivered/{donationId}")
    public String setDonationAsDelivered(@PathVariable String donationId) {
        donationService.setDonationAsDelivered(donationId);
        return "redirect:/courier/donation-list";
    }
}
