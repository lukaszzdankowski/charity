package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;

@Controller
@RequestMapping("/admin/CRUD/institution")
@RequiredArgsConstructor
public class InstitutionCRUDController {
    private final InstitutionService institutionService;

    @GetMapping("/list")
    public String showInstitutions(Model model) {
        List<Institution> institutions = institutionService.getInstitutions();
        model.addAttribute("institutions", institutions);
        return "admin/CRUD/institutions/list";
    }

    @GetMapping("/add")
    public String showAddInstitution(Model model) {
        Institution institution = new Institution();
        model.addAttribute("institution", institution);
        return "admin/CRUD/institutions/add-form";
    }

    @PostMapping("/save")
    public String processAddInstitution(Institution institution) {
        institutionService.saveInstitution(institution);
        return "redirect:/admin/CRUD/institution/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditInstitution(@PathVariable Long id, Model model) {
        Institution institution = institutionService.getInstitution(id);
        model.addAttribute("institution", institution);
        return "admin/CRUD/institutions/edit-form";
    }

    @GetMapping("/disable/{id}")
    public String removeInstitution(@PathVariable Long id) {
        institutionService.disableInstitution(id);
        return "redirect:/admin/CRUD/institution/list";
    }

    @GetMapping("/enable/{id}")
    public String enableInstitution(@PathVariable Long id) {
        institutionService.enableInstitution(id);
        return "redirect:/admin/CRUD/institution/list";
    }
}
