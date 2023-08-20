package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Database.repository.CandidateRepository;
import com.itjobapp.Security.UserEntity;
import com.itjobapp.Security.UserJpaRepository;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.SkillList;
import com.itjobapp.Service.domain.Skills;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
public class DashboardController {
    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            String role = userService.findRoleByMail(email);

            if(role.equals("CANDIDATE")) {
                CandidateDTO candidate = candidateMapper.map(candidateService.findCandidateByEmail(email));
                model.addAttribute("candidate", candidate);
                return "dashboard-candidate";
            } else if(role.equals("COMPANY")) {
                CompanyDTO company = companyMapper.map(companyService.getCompanyByEmail(email));

                if(company.getIsHiring() == null) {
                    company = company.builder().isHiring(false).build();
                }

                Set<JobOfferDTO> jobOffers = company.getJobOffers();

                model.addAttribute("company", company);
                model.addAttribute("jobOffers", jobOffers);

                return "dashboard-company";
            }

        }

        return "home";
    }

    private String getHighestUserRole(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("CANDIDATE")) {
                return "CANDIDATE";
            } else if (authority.getAuthority().equals("COMPANY")) {
                return "COMPANY";
            }
        }
        return "Unknown";
    }

    @GetMapping("/edit-profile")
    public String showEditProfile(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            String role = getHighestUserRole(authentication);
            if(role.equals("CANDIDATE")) {
                CandidateDTO candidate = candidateMapper.map(candidateService.findCandidateByEmail(email));
                model.addAttribute("allSkills", ServiceController.getAllSkillsAsSkillSet());
                model.addAttribute("candidate", candidate);
                return "dashboard-candidate-edit-profile";
            } else if(role.equals("COMPANY")) {
                CompanyDTO company = companyMapper.map(companyService.getCompanyByEmail(email));
                model.addAttribute("company", company);
                return "dashboard-company-edit-profile";
            }

        }

        return "home";
    }





}
