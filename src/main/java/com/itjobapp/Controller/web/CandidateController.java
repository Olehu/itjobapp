package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Company;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class CandidateController {

    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;
    private final UserService userService;

    @GetMapping(value = "/candidate")
    public String candidatePage(
            @RequestParam(name = "skills", required = false) String skills,
            @RequestParam(name = "available", required = false) Boolean available,
            Model model
    ) {
        List<CandidateDTO> candidates;
        if (skills != null || available != null) {
            candidates = candidateService.searchCandidates(skills, available).stream()
                    .map(candidateMapper::map)
                    .toList();
        } else {
            candidates = candidateService.getAllCandidates().stream()
                    .map(candidateMapper::map)
                    .toList();
        }
        model.addAttribute("candidates", candidates);
        return "candidate";
    }


    @GetMapping(value = "/candidate/new")
    public String showCandidateForm(Model model) {
        model.addAttribute("candidate", new CandidateDTO());
        return "candidate-form";
    }

    @PostMapping(value = "/candidate/new")
    public String createcandidateDTO(@ModelAttribute("candidate") CandidateDTO candidateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "candidate";
        }

        candidateService.createCandidate(candidateMapper.maper(candidateDTO));
        return "redirect:/candidate";
    }


    @GetMapping(value = "/candidate/profile/{candidateEmail}")
    public String showCandidateProfile(
            @PathVariable String candidateEmail,
            Authentication authentication
            , Model model) {
        Boolean isCompany = false;
        if (authentication != null && authentication.isAuthenticated()  && userService.findRoleByMail(authentication.getName()).equals("COMPANY")) {


                isCompany = true;
        }

        Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);

        model.addAttribute("isCompany", isCompany);
        model.addAttribute("candidate", candidate);

        return "candidate-profile";
    }



    @PostMapping("/hire/{candidateEmail}")
    public String hireCandidate(@PathVariable String candidateEmail) {
        Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);
        if (candidate != null) {
            candidate = candidate.withAvailable(false);
            candidateService.update(candidate);
        }
        return "redirect:/candidate/{candidateEmail}";
    }

    @GetMapping(value = "/candidate/profile/image/{candidateEmail}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getCandidateProfileImage(@PathVariable String candidateEmail) {
        Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);
        if (candidate != null && candidate.getProfileImage() != null) {
            return ResponseEntity.ok(candidate.getProfileImage());
        }
        return ResponseEntity.notFound().build();
    }

}
