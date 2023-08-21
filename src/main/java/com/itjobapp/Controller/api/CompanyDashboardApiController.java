package com.itjobapp.Controller.api;

import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.JobOfferService;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@AllArgsConstructor
public class CompanyDashboardApiController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final JobOfferService jobOfferService;
    private final JobOfferMapper jobOfferMapper;
    private final UserService userService;

    @PostMapping("/company-edit-profile")
    public String editCompanyProfile(Authentication authentication, @RequestBody CompanyDTO companyDTO) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();

            Company existingCompany = companyService.getCompanyByEmail(email);
            if (existingCompany.getEmail() != companyDTO.getEmail()) {
                userService.updateEmail(companyDTO.getEmail());
            }

            existingCompany = existingCompany
                    .withCompanyName(companyDTO.getCompanyName())
                    .withIndustry(companyDTO.getIndustry())
                    .withCity(companyDTO.getCity())
                    .withJobOffers(companyMapper.maper(companyDTO).getJobOffers())
                    .withIsHiring(companyDTO.getIsHiring())
                    .withEmail(companyDTO.getEmail());

            companyService.update(existingCompany);
            return "Company profile updated successfully";
        }

        return "Unauthorized";
    }

    @GetMapping("/joboffer/profile-view/{name}")
    public JobOfferDTO showJobofferDetails(Authentication authentication, @PathVariable String name) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            if (email.equals(jobOfferService.getJobOfferByName(name).getCompany().getEmail())) {
                return jobOfferMapper.map(jobOfferService.getJobOfferByName(name));
            }
        }
        throw new RuntimeException("Unauthorized");
    }


    @PostMapping("/joboffer/profile-edit")
    public String editJoboffer(Authentication authentication, @RequestBody JobOfferDTO jobOfferDTO) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();

            if (email.equals(jobOfferService.getJobOfferByName(jobOfferDTO.getName()).getCompany().getEmail())) {
                JobOffer existingJobOffer = jobOfferService.getJobOfferByName(jobOfferDTO.getName())
                        .withSkills(jobOfferMapper.maper(jobOfferDTO).getSkills())
                        .withExperienceLevel(jobOfferDTO.getExperienceLevel())
                        .withOtherRequirements(jobOfferDTO.getOtherRequirements());

                jobOfferService.update(existingJobOffer);
                return "Job offer updated successfully";
            }
        }
        return "Unauthorized";
    }

    @DeleteMapping("/joboffer/profile-delete/{name}")
    public String deleteJoboffer(Authentication authentication, @PathVariable String name) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();

            if (email.equals(jobOfferService.getJobOfferByName(name).getCompany().getEmail())) {
                jobOfferService.delete(name);
                return "Job offer deleted successfully";
            }
        }
        return "Unauthorized";
    }
}
