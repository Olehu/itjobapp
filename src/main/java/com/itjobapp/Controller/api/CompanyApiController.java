package com.itjobapp.Controller.api;

import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/companies")
public class CompanyApiController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final JobOfferMapper jobOfferMapper;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getCompanies(
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "isHiring", required = false) Boolean isHiring,
            @RequestParam(name = "jobOffers", required = false) Boolean hasJobOffers
    ) {
        List<CompanyDTO> companies;
        if (location != null || isHiring != null || hasJobOffers != null) {
            companies = companyService.searchCompanies(location, isHiring, hasJobOffers).stream()
                    .map(companyMapper::map)
                    .toList();
        } else {
            companies = companyService.getAllCompanies().stream()
                    .map(companyMapper::map)
                    .toList();
        }
        return ResponseEntity.ok(companies);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        companyService.createCompany(companyMapper.maper(companyDTO));
        return ResponseEntity.ok(companyDTO);
    }

    @GetMapping("/{companyName}")
    public ResponseEntity<CompanyDTO> getCompanyProfile(@PathVariable String companyName) {
        CompanyDTO company = companyMapper.map(companyService.getCompanyByName(companyName));
        if (company != null) {
            return ResponseEntity.ok(company);
        }
        return ResponseEntity.notFound().build();
    }
}
