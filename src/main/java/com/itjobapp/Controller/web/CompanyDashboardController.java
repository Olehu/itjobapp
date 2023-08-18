package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.JobOfferService;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class CompanyDashboardController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final JobOfferService jobOfferService;
    private final JobOfferMapper jobOfferMapper;
    private final UserService userService;


    @PostMapping("/dashboard/company-edit-profile")
    public String editCompanyProfile(Authentication authentication, @ModelAttribute CompanyDTO companyDTO) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();

            Company existingCompany = companyService.getCompanyByEmail(email);
            if (existingCompany.getEmail()!=companyDTO.getEmail()){
                userService.updateEmail(companyDTO.getEmail());
            }

             existingCompany = existingCompany
                    .withCompanyName(companyDTO.getCompanyName())
                    .withIndustry(companyDTO.getIndustry())
                    .withLocation(companyDTO.getLocation())
                    .withJobOffers(companyMapper.maper(companyDTO).getJobOffers())
                    .withIsHiring(companyDTO.getIsHiring())
                    .withEmail(companyDTO.getEmail());

            companyService.update(existingCompany);
            return "redirect:/dashboard";
        }

        return "redirect:/home";
    }

    @GetMapping(value = "/dashboard/joboffer/profile-view/{name}")
    public String showJobofferDetails(Authentication authentication, @PathVariable String name, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {


            String email = authentication.getName();
            if (email.equals(jobOfferService.getJobOfferByName(name).getCompany().getEmail())) {
                JobOfferDTO jobOfferDTO = jobOfferMapper.map(jobOfferService.getJobOfferByName(name));

                model.addAttribute("jobOffer", jobOfferDTO);
                model.addAttribute("JobOffer", jobOfferMapper.map(jobOfferService.getJobOfferByName(name)));
                return "dashboard-company-joboffer";
            }
        }
        return "error";
    }

    @GetMapping(value = "/dashboard/joboffer/profile-edit/{name}")
    public String showJobOfferFormForCompany(Authentication authentication, @PathVariable String name, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {

            String email = authentication.getName();
            if (email.equals(jobOfferService.getJobOfferByName(name).getCompany().getEmail())) {
                JobOfferDTO jobOfferDTO = jobOfferMapper.map(jobOfferService.getJobOfferByName(name));
                model.addAttribute("allSkills", ServiceController.getAllSkills());
                model.addAttribute("jobOfferDTO", jobOfferDTO);
                return "dashboard-company-edit-joboffer";
            }
        }
        return "error";
    }

    @PostMapping("/dashboard/joboffer/profile-edit")
    public String editJoboffer(Authentication authentication, @ModelAttribute JobOfferDTO jobOfferDTO) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();

            if (email.equals(jobOfferService.getJobOfferByName(jobOfferDTO.getName()).getCompany().getEmail())) {


                JobOffer existingJobOffer = jobOfferService.getJobOfferByName(jobOfferDTO.getName())
                        .withSkills(jobOfferDTO.getSkills())
                        .withExperienceLevel(jobOfferDTO.getExperienceLevel())
                        .withOtherRequirements(jobOfferDTO.getOtherRequirements());

                JobOfferDTO updated = jobOfferMapper.map(existingJobOffer);

                jobOfferService.update(updated);
                return "redirect:/dashboard";
            }
        }
        return "redirect:/home";
    }

    @DeleteMapping("/dashboard/joboffer/profile-delete/{name}")
    public String deleteJoboffer(Authentication authentication, @PathVariable String name) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();

            if (email.equals(jobOfferService.getJobOfferByName(name).getCompany().getEmail())) {
                jobOfferService.delete(name);
            }
        }
        return "redirect:/dashboard";

    }
}
