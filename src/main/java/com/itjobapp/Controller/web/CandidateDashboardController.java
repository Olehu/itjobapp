package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CandidateService;
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
                    .withAvailable(candidateDTO.getAvailable());


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
//    @Value("${image.upload.path}")
//    private String uploadPath;

    @PostMapping("/upload-image")
    public String uploadProfileImage(
            Authentication authentication,
            @RequestParam("image") MultipartFile imageFile) {
        String uploadPath = "/static/images/profile/";
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();


            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
            candidateService.setProfileImage(fileName, email);


            String uploadDir = "/images/profile/" + candidateService.findCandidateByEmail(email).getFirstName();

            try {
                com.image.fileupload.util.ImageUtil.saveFile(uploadDir, fileName, imageFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return "dashboard";












//            try {
//                // Sprawdź rozmiar pliku
//                if (imageFile.getSize() > 1_000_000) {
//                    // Obsługa błędu
//                    return "redirect:/upload-image?error=File size too large. Max allowed size is 1MB.";
//                }
//
//                // Wygeneruj nazwę pliku na podstawie adresu e-mail
//                String imageName = email + ".jpg"; // Możesz dostosować rozszerzenie
//
//                // Usuń istniejące zdjęcie o tej samej nazwie
//                File existingImage = new File(uploadPath + imageName);
//                if (existingImage.exists()) {
//                    existingImage.delete();
//                }
//
//                // Zapisz nowe zdjęcie
//                File newImage = new File(uploadPath + imageFile.getName());
//                imageFile.transferTo(newImage);
//
//                // Aktualizacja ścieżki do zdjęcia w bazie danych
//                candidateService.setProfileImage(imageName, email);
//
//                return "redirect:/dashboard"; // Przekierowanie na stronę główną po udanym wgrywaniu
//            } catch (IOException e) {
//                e.printStackTrace();
//                // Obsługa błdu
//                return "redirect:/upload-image?error=An error occurred while uploading the profile image.";
//            }
        }

        return "redirect:/home";
    }
}

