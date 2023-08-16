package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Security.UserJpaRepository;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Company;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;


@Controller
@AllArgsConstructor
public class DashboardController {
    private final UserService userService;
    private final UserJpaRepository userJpaRepository;
    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            String role = getHighestUserRole(authentication);


            if(role.equals("CANDIDATE")) {
                CandidateDTO candidate = candidateMapper.map(candidateService.getCandidateByEmail(email));
                model.addAttribute("candidate", candidate);
                return "dashboard-candidate";
            } else if(role.equals("COMPANY")) {
                CompanyDTO company = companyMapper.map(companyService.getCompanyByEmail(email));
                model.addAttribute("company", company);
                return "dashboard-company";
            }

        }

        return "/";
    }

    private String getHighestUserRole(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("CANDIDATE")) {
                return "CANDIDATE";
            } else if (authority.getAuthority().equals("COMPANY")) {
                return "COMPANY";
            }
            // Add more roles if needed
        }
        return "Unknown";
    }
}
