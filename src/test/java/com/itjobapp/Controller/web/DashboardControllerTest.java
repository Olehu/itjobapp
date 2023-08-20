package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.web.DashboardController;
import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Security.UserService;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DashboardController.class)
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private CompanyService companyService;

    @MockBean
    private CandidateMapper candidateMapper;

    @MockBean CompanyMapper companyMapper;

    @Test
    @WithMockUser(username = "test@example.com", roles = {"CANDIDATE"})
    public void testShowDashboardWithCandidateRole() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("test@example.com");

        when(userService.findRoleByMail("test@example.com")).thenReturn("CANDIDATE");

        Candidate candidate = Entities.getCandidateJunior();
        CandidateDTO candidateDTO = Entities.getCandidateDTOJunior();

        when(candidateService.findCandidateByEmail("test@example.com")).thenReturn(candidate);
        when(candidateMapper.map(candidate)).thenReturn(candidateDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/dashboard")
                        .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard-candidate"))
                .andExpect(model().attribute("candidate", candidateDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/edit-profile")
                        .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard-candidate-edit-profile"))
                .andExpect(model().attribute("allSkills", ServiceController.getAllSkillsAsSkillSet()))
                .andExpect(model().attribute("candidate", candidateDTO));
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = {"COMPANY"})
    public void testShowDashboardWithCompanyRole() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("test@example.com");

        when(userService.findRoleByMail("test@example.com")).thenReturn("COMPANY");

        Company company = Entities.getCompanyABC();
        CompanyDTO companyDTO = Entities.getCompanyDTOABC();

        when(companyService.getCompanyByEmail("test@example.com")).thenReturn(company);
        when(companyMapper.map(company)).thenReturn(companyDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/dashboard")
                        .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard-company"))
                .andExpect(model().attribute("company", companyDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/edit-profile")
                        .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard-company-edit-profile"))
                .andExpect(model().attribute("company", companyDTO));
    }


}
