package pl.coderslab.charity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.RoleService;
import pl.coderslab.charity.service.UserService;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin/CRUD/user")
@RequiredArgsConstructor
public class UserCRUDController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/list")
    public String showUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin/CRUD/users/list";
    }

    @GetMapping("/add")
    public String showAddUser(Model model) {
        User user = new User();
        model.addAttribute(user);
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "admin/CRUD/users/add-form";
    }

    @PostMapping("/save")
    public String saveUser(User user) throws MessagingException {
        userService.saveCreatedUser(user);
        return "redirect:/admin/CRUD/user/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditUser(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "admin/CRUD/users/edit-form";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        userService.saveUserWithOldPassword(user);
        return "redirect:/admin/CRUD/user/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Principal principal) {
        if (principal.getName().equals(userService.getUser(id).getEmail())) {
            return "admin/CRUD/users/cannot-delete";
        }
        userService.deleteUser(id);
        return "redirect:/admin/CRUD/user/list";
    }
}
