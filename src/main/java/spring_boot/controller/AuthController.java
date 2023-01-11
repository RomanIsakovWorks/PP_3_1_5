package spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping(value = "/")
    public String startPage() {
        return "redirect:/loginPage";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }
}
