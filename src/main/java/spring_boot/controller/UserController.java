package spring_boot.controller;


import org.springframework.security.core.userdetails.UserDetailsService;
import spring_boot.model.User;
import spring_boot.service.RoleService;
import spring_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring_boot.service.UserServiceImpl;
import java.security.Principal;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, UserDetailsService userDetailsService, UserServiceImpl userServiceImpl, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model, Principal principal) {
        User userAdmin = userService.findByEmail(principal.getName());
        model.addAttribute("userAdmin", userAdmin);
        model.addAttribute("allUsers", userService.getListUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "adminPage";
    }

    @GetMapping("/admin/addUser")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "redirect:/admin";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/editUser")
    public String editUser(Model model, @RequestParam int id) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "redirect:/admin";
    }

    @PostMapping("/admin/editUser")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") int id){
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "userPage";
    }
}
