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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;


    private static final String PROFILE = "/company/profile";
    private static final String NEW_PROJECT = "/company/new-project";

//    private static final String REGISTER = "/company-form";


    @GetMapping(value = "/company")
    public String companyPage (Model model){
        var allCompany = companyService.getAllCompanies().stream()
                .map(companyMapper::map)
                .toList();
        model.addAttribute("companies", allCompany);
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
//            throw new RuntimeException("Company not found: " + companyName);
            // Obsłuż przypadek, gdy firma nie została znaleziona
            // Możesz przekierować na stronę błędu lub inaczej obsłużyć ten przypadek
            return "company-not-found";
        }
    }
}
