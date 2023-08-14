package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.JobOfferService;
import com.itjobapp.Service.domain.Company;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
public class JobOfferControler {

    private final JobOfferService jobOfferService;

    private final JobOfferMapper jobOfferMapper;

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;






    @GetMapping(value = "/joboffer")
    public String jobOfferPage (Model model){
        var allJobOffer = jobOfferService.getAllJobOffer().stream()
                .map(jobOfferMapper::map)
                .toList();
        model.addAttribute("jobOffers", allJobOffer);
        return "joboffer";
    }


    @GetMapping(value ="/joboffer/new")
    public String showJobOfferForm(Model model) {
        model.addAttribute("jobOffer", new JobOfferDTO());
        return "joboffer-form";
    }





    @GetMapping(value ="/joboffer/new/{companyName}")
    public String showJobOfferFormForCompany(@PathVariable String companyName, Model model) {
        Company company = companyService.getCompanyByName(companyName);

        if (company != null) {
            JobOfferDTO jobOfferDTO = new JobOfferDTO();
            jobOfferDTO.setCompany(companyMapper.map(company));
            model.addAttribute("jobOffer", jobOfferDTO);
            return "joboffer-form";
        } else {
            return "error";
        }
    }

    @PostMapping(value ="/joboffer/new")
    public String createjobOfferDTO(
            @ModelAttribute("jobOffer") JobOfferDTO jobOfferDTO,
            @RequestParam("companyName") String companyName,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("Binding result has errors {}", bindingResult.getAllErrors());
            return "joboffer";
        }

        Company company = companyService.getCompanyByName(companyName);
        if (company != null) {
            jobOfferDTO.setCompany(companyMapper.map(company));
            jobOfferService.createJobOffer(jobOfferMapper.maper(jobOfferDTO));
        } else {
            log.error("Company not found");
            return "error";
        }
//        if (jobOfferDTO.getCompany() != null) {
//            Company company = companyMapper.maper(jobOfferDTO.getCompany());
//
//            jobOfferDTO.setCompany(jobOfferDTO.getCompany());
//        }

        jobOfferService.createJobOffer(jobOfferMapper.maper(jobOfferDTO));
        return "redirect:/joboffer";
    }



}
