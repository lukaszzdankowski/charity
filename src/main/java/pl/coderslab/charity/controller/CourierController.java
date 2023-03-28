package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courier")
@RequiredArgsConstructor
public class CourierController {
    @GetMapping("/home")
    public String adminHome() {
        return "courier/home-courier";
    }

//    @GetMapping("/donation-list")
//    public String showDonations(Model model) {
//        List<Donation> donations = donationService.listAllDonations();
//        model.addAttribute("donations", donations);
//        return "admin/donation-list";
//    }
//
//    @GetMapping("/donation-archived/{donationId}")
//    public String setDonationAsArchived(@PathVariable String donationId) {
//        donationService.setDonationAsArchived(donationId);
//        return "redirect:/admin/donation-list";
//    }
}
