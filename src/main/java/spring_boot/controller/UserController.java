package spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class  UserController {

    @GetMapping("/admin")
    public String showAdminPage() {
        return "adminPage";
    }

    @GetMapping("/user")
    public String showUserPage(){
        return "userPage";
    }
}
