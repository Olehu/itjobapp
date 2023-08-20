package com.itjobapp.Controller.web;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class HomeController {

    private static final String HOME = "/";

    @RequestMapping(value = HOME, method = RequestMethod.GET)
    public String homePage(Model model, Authentication authentication) {
        boolean isUserLoggedIn = false;
        if (authentication != null && authentication.isAuthenticated()) {
            isUserLoggedIn = true;
        }
        model.addAttribute("isUserLoggedIn", isUserLoggedIn);

        return "home";
    }
}
