package com.itjobapp.Controller.web;

import com.itjobapp.Security.UserJpaRepository;
import com.itjobapp.Security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class DashboardController {
    private final UserService userService;
    private final UserJpaRepository userJpaRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            String role = getHighestUserRole(authentication);

            model.addAttribute("username", username);
            model.addAttribute("role", role);

            // Get user profile based on username
            // Replace this with your logic to fetch user profile
            Object userProfile = userJpaRepository.findByEmail(username);
            model.addAttribute("userProfile", userProfile);
        }

        return "dashboard";
    }

    private String getHighestUserRole(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("CANDIDATE")) {
                return "Candidate";
            } else if (authority.getAuthority().equals("COMPANY")) {
                return "Company";
            }
            // Add more roles if needed
        }
        return "Unknown";
    }
}
