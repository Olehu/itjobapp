package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Security.UserService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyDashboardController.class)
public class CompanyDashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @MockBean
    private CompanyMapper companyMapper;

    @MockBean
    private JobOfferService jobOfferService;

    @MockBean
    private JobOfferMapper jobOfferMapper;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "company@example.com", roles = {"COMPANY"})
    public void testEditCompanyProfile() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("company@example.com");

        Company existingCompany = Entities.getCompanyXYZ();

        when(companyService.getCompanyByEmail("company@example.com")).thenReturn(existingCompany);

        CompanyDTO companyDTO = Entities.getCompanyDTOABC();

        Company updatedCompany = existingCompany
                .withCompanyName(companyDTO.getCompanyName())
                .withIndustry(companyDTO.getIndustry())
                .withCity(companyDTO.getCity())
                .withIsHiring(companyDTO.getIsHiring())
                .withEmail(companyDTO.getEmail());


        when(companyMapper.maper(any(CompanyDTO.class))).thenReturn(updatedCompany);

        mockMvc.perform(post("/dashboard/company-edit-profile")
                        .principal(authentication)
                        .param("companyName", companyDTO.getCompanyName())
                        .param("industry", companyDTO.getIndustry())
                        .param("city", companyDTO.getCity())
                        .param("isHiring", String.valueOf(companyDTO.getIsHiring()))
                        .param("email", companyDTO.getEmail())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));

        verify(companyService).update(any(Company.class));
    }

//    @Test
//    @WithMockUser(username = "company@example.com", roles = {"COMPANY"})
//    public void testShowJobofferDetails() throws Exception {
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.isAuthenticated()).thenReturn(true);
//        when(authentication.getName()).thenReturn("company@example.com");
//
//        Company company = Entities.getCompanyABC().withEmail("company@example.com");
//        CompanyDTO companyDTO = Entities.getCompanyDTOABC().withEmail("company@example.com");
//        JobOffer jobOffer = Entities.jobOfferJunior().withCompany(company).withName("test");
//
//        when(jobOfferService.getJobOfferByName("test")).thenReturn(jobOffer);
//        JobOfferDTO jobOfferDTO = Entities.jobOfferDTOJunior().withCompany(companyDTO).withName("test");
//
//
//        mockMvc.perform(get("/dashboard/joboffer/profile-view/test")
//                        .principal(authentication)
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("dashboard-company-joboffer"))
//                .andExpect(model().attribute("jobOffer", jobOfferDTO));;
//
//        verify(jobOfferService).getJobOfferByName("test");
//    }
//
//    @Test
//    @WithMockUser(username = "company@example.com", roles = {"COMPANY"})
//    public void testShowJobOfferFormForCompany() throws Exception {
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.isAuthenticated()).thenReturn(true);
//        when(authentication.getName()).thenReturn("company@example.com");
//
//        Company company = Entities.getCompanyABC().withEmail("company@example.com");
//        JobOffer jobOffer = Entities.jobOfferJunior().withCompany(company);
//
//        when(jobOfferService.getJobOfferByName("job-name")).thenReturn(jobOffer);
//
//        mockMvc.perform(get("/dashboard/joboffer/profile-edit/job-name")
//                        .principal(authentication)
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("dashboard-company-edit-joboffer"));
//
//        verify(jobOfferService).getJobOfferByName("job-name");
//    }

    @Test
    @WithMockUser(username = "company@example.com", roles = {"COMPANY"})
    public void testEditJoboffer() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("company@example.com");

        Company company = Entities.getCompanyABC().withEmail("company@example.com");

        JobOffer jobOffer = Entities.jobOfferJunior().withCompany(company);

        JobOfferDTO jobOfferDTO = Entities.jobOfferDTOJunior().withName("job-name");

        when(jobOfferService.getJobOfferByName("job-name")).thenReturn(jobOffer);
        when(jobOfferMapper.maper(any(JobOfferDTO.class))).thenReturn(jobOffer);

        mockMvc.perform(post("/dashboard/joboffer/profile-edit")
                        .principal(authentication)
                        .flashAttr("jobOfferDTO", jobOfferDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));

        verify(jobOfferService).update(any(JobOffer.class));
    }

    @Test
    @WithMockUser(username = "company@example.com", roles = {"COMPANY"})
    public void testDeleteJoboffer() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("company@example.com");

        Company company = Entities.getCompanyABC().withEmail("company@example.com");
        JobOffer jobOffer = Entities.jobOfferJunior().withCompany(company);

        when(jobOfferService.getJobOfferByName("job-name")).thenReturn(jobOffer);

        mockMvc.perform(delete("/dashboard/joboffer/profile-delete/job-name")
                        .principal(authentication)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));

        verify(jobOfferService).delete("job-name");
    }

}