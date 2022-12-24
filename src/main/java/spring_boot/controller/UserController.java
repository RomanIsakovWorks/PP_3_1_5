package spring_boot.controller;


import spring_boot.model.User;
import spring_boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getListUsers());
        return "users";
    }

    @GetMapping("/addUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/editUser")
    public String editUser(Model model, @RequestParam int id) {
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
    }

    @PostMapping("/editUser")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") int id){
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
