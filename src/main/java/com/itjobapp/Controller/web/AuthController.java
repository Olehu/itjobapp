package com.itjobapp.Controller.web;

import com.itjobapp.Security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam("userName") String userName,
                            @RequestParam("password") String password) {
        userService.authenticateUser(userName, password);
        return "redirect:/dashboard";

    }


    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("role") String role) {
        userService.registerUser(email, password, role);
        return "redirect:/login";
    }

}
