package com.itjobapp.Controller.api;

import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.JobOfferService;
import com.itjobapp.Service.domain.Company;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/joboffers")
public class JobOfferApiController {

    private final JobOfferService jobOfferService;
    private final JobOfferMapper jobOfferMapper;
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @GetMapping
    public ResponseEntity<List<JobOfferDTO>> getJobOffers(
            @RequestParam(name = "experienceLevel", required = false) String experienceLevel,
            @RequestParam(name = "remote", required = false) String remote,
            @RequestParam(name = "skills", required = false) Set<String> skills
    ) {
        List<JobOfferDTO> jobOffers;
        if (experienceLevel != null || remote != null || skills != null) {
            jobOffers = jobOfferService.searchJobOffers(experienceLevel, remote, skills).stream()
                    .map(jobOfferMapper::map)
                    .toList();
        } else {
            jobOffers = jobOfferService.getAllJobOffer().stream()
                    .map(jobOfferMapper::map)
                    .toList();
        }
        return ResponseEntity.ok(jobOffers);
    }

    @PostMapping
    public ResponseEntity<JobOfferDTO> createJobOffer(
            @RequestBody JobOfferDTO jobOfferDTO,
            @RequestParam("companyName") String companyName
    ) {
        Company company = companyService.getCompanyByName(companyName);
        jobOfferDTO.setCompany(companyMapper.map(company));
        jobOfferService.createJobOffer(jobOfferMapper.maper(jobOfferDTO));
        return ResponseEntity.ok(jobOfferDTO);
    }

    @GetMapping("/{name}")
    public ResponseEntity<JobOfferDTO> getJobOfferProfile(@PathVariable String name) {
        JobOfferDTO jobOffer = jobOfferMapper.map(jobOfferService.getJobOfferByName(name));
        if (jobOffer != null) {
            return ResponseEntity.ok(jobOffer);
        }
        return ResponseEntity.notFound().build();
    }
}
