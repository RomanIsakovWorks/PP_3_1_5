package spring_boot.controller;


import org.springframework.security.core.userdetails.UserDetailsService;
import spring_boot.model.User;
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

    public UserController(UserService userService, UserDetailsService userDetailsService, UserServiceImpl userServiceImpl) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getListUsers());
        return "users";
    }

    @GetMapping("/admin/addUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping("/admin")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/editUser")
    public String editUser(Model model, @RequestParam int id) {
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
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
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
