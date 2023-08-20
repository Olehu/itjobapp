package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.JobOfferService;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobOfferControler.class)
@ExtendWith(SpringExtension.class)

public class JobOfferControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobOfferService jobOfferService;

    @MockBean
    private JobOfferMapper jobOfferMapper;

    @MockBean
    private CompanyService companyService;

    @MockBean
    private CompanyMapper companyMapper;


    @Test
    @WithMockUser
    public void testJobOfferPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/joboffer"))
                .andExpect(status().isOk())
                .andExpect(view().name("joboffer"))
                .andExpect(model().attributeExists("allSkills", "jobOffers"));
    }

    @Test
    @WithMockUser
    public void testShowJobOfferFormForCompany() throws Exception {
        Company company = Entities.getCompanyABC();
        when(companyService.getCompanyByName(company.getCompanyName())).thenReturn(company);
JobOfferDTO jobOfferDTO = new JobOfferDTO().withCompany(Entities.getCompanyDTOABC());

        when(companyMapper.map(any())).thenReturn(Entities.getCompanyDTOABC());

        mockMvc.perform(MockMvcRequestBuilders.get("/joboffer/new/" + company.getCompanyName()))
                .andExpect(status().isOk())
                .andExpect(view().name("joboffer-form"))
                .andExpect(model().attribute("allSkills", ServiceController.getAllSkillsAsSkillSet()))
                .andExpect(model().attribute("jobOffer", jobOfferDTO));
    }

    @Test
    @WithMockUser
    public void testCreateJobOfferDTO() throws Exception {

        JobOfferDTO jobOfferDTO = Entities.jobOfferDTOJunior();
        Company company = Entities.getCompanyABC();
        when(companyService.getCompanyByName(company.getCompanyName())).thenReturn(company);
        when(companyMapper.map(company)).thenReturn(Entities.getCompanyDTOABC());

        mockMvc.perform(MockMvcRequestBuilders.post("/joboffer/new")
                        .param("companyName", company.getCompanyName())
                        .flashAttr("jobOffer", jobOfferDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/joboffer"));
    }


}