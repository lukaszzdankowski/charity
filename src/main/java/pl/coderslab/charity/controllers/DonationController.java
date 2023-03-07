package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DonationController {

    @GetMapping("/form")
    public String formDisplay(){
        return "form";
    }
}
