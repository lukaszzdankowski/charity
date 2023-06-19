package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/admin/CRUD/category")
@RequiredArgsConstructor
public class CategoryCRUDController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "admin/CRUD/categories/list";
    }

    @GetMapping("/add")
    public String showAddCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/CRUD/categories/add-form";
    }

    @PostMapping("/save")
    public String saveCategory(Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/CRUD/category/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategory(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategory(id);
        model.addAttribute("category", category);
        return "admin/CRUD/categories/edit-form";
    }

    @GetMapping("/disable/{id}")
    public String disableCategory(@PathVariable Long id) {
        categoryService.disableCategory(id);
        return "redirect:/admin/CRUD/category/list";
    }

    @GetMapping("/enable/{id}")
    public String enableCategory(@PathVariable Long id) {
        categoryService.enableCategory(id);
        return "redirect:/admin/CRUD/category/list";
    }
}
