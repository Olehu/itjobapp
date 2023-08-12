package com.itjobapp.Controller.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class Company {

    private static final String COMPANY = "/company";
    private static final String PROFILE = "/company/profile";
    private static final String NEW_PROJECT = "/company/new-project";


    @GetMapping(value = PROFILE)
    public ModelAndView companyProfilePage() {

    }

    @PostMapping(value = NEW_PROJECT)
    public ModelAndView companyProfilePage() {
    }
}
