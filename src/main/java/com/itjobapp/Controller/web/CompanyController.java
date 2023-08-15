package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.shaded.freemarker.ext.beans.MapModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;


    @GetMapping("/company")
    public String companyPage(
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "isHiring", required = false) Boolean isHiring,
            @RequestParam(name = "jobOffers", required = false) Boolean hasJobOffers,
            Model model
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

        model.addAttribute("companies", companies);
        return "company";
    }


    @GetMapping(value ="/company/new")
    public String showCompanyForm(Model model) {
        model.addAttribute("company", new CompanyDTO());
        return "company-form";
    }

    @PostMapping(value ="/company/new")
    public String createcompanyDTO(@ModelAttribute("company") CompanyDTO companyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "company";
        }

        companyService.createCompany(companyMapper.maper(companyDTO));
        return "redirect:/company";
    }


    @GetMapping(value = "/company/profile/{companyName}")
    public String showCompanyProfile(@PathVariable String companyName, Model model) {
        Optional<Company> companyOptional = Optional.of(companyService.getCompanyByName(companyName));

        if (companyOptional.isPresent()) {
            model.addAttribute("company", companyOptional.get());
            return "company-profile";
        } else {

            return "company-not-found";
        }
    }
}
