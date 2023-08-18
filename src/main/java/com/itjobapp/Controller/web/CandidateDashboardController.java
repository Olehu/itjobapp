package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.ImageService;
import com.itjobapp.Service.domain.Candidate;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class CandidateDashboardController {

    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;
    private final UserService userService;
    private final ImageService imageService;

    @PostMapping("/dashboard/candidate-edit-profile")
    public String editProfile(Authentication authentication, @ModelAttribute CandidateDTO candidateDTO) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();

            Candidate existingCandidate = candidateService.findCandidateByEmail(email);
            if (existingCandidate.getEmail()!=candidateDTO.getEmail()){
                userService.updateEmail(candidateDTO.getEmail());
            }

            existingCandidate = existingCandidate
                    .withFirstName(candidateDTO.getFirstName())
                    .withLastName(candidateDTO.getLastName())
                    .withEmail(candidateDTO.getEmail())
                    .withSkills(candidateDTO.getSkills())
                    .withPhoneNumber(candidateDTO.getPhoneNumber())
                    .withAvailable(candidateDTO.getAvailabilityStatus());


            candidateService.update(existingCandidate);
            return "redirect:/dashboard";
        }

        return "redirect:/home";
    }

    @GetMapping("upload-image")
    public String uploadImageForm(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();


            CandidateDTO candidate = candidateMapper.map(candidateService.findCandidateByEmail(email));
            model.addAttribute("candidate", candidate);
            return "upload-image";
        }


        return "home";
    }


    @PostMapping("/upload-image")
    public String uploadProfileImage(
            Authentication authentication,
            @RequestParam("image") MultipartFile imageFile) {
        String uploadPath = "/static/images/profile/";
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            candidateService.saveImage(imageFile, candidateService.findCandidateByEmail(email));
                return "redirect:/dashboard";
        }

        return "redirect:/dashboard";
    }
}

