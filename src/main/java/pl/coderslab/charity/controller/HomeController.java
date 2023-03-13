package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.EmailSenderService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

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
    private final TokenRepository tokenRepository;

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutionList", institutionService.getInstitutions());
        model.addAttribute("quantityTotal", donationService.countTotalQuantity());
        model.addAttribute("donationsTotal", donationService.countTotalDonations());
        return "index";
    }

    @GetMapping("/router")
    public String loginRedirect(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {//czy to nie problem, że ma dwie role
            return "redirect:admin/home";
        }
        return "redirect:user/home";
    }

}
