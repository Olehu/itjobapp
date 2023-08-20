package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.domain.Candidate;
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

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CandidateController.class)
public class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private CandidateMapper candidateMapper;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "candidate@example.com", roles = {"CANDIDATE"})
    public void testCandidatePage() throws Exception {
        when(candidateService.getAllCandidates()).thenReturn(List.of(Entities.getCandidateJunior()));
        when(candidateMapper.map(any())).thenReturn(Entities.getCandidateDTOJunior());

        mockMvc.perform(MockMvcRequestBuilders.get("/candidate"))
                .andExpect(status().isOk())
                .andExpect(view().name("candidate"))
                .andExpect(model().attributeExists("candidates", "allSkills"));
    }

    @Test
    @WithMockUser(username = "candidate@example.com", roles = {"CANDIDATE"})
    public void testShowCandidateForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/candidate/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("candidate-form"))
                .andExpect(model().attributeExists("allSkills", "candidate"));
    }

    @Test
    @WithMockUser(username = "candidate@example.com", roles = {"CANDIDATE"})
    public void testCreateCandidateDTO() throws Exception {
        CandidateDTO candidateDTO = Entities.getCandidateDTOJunior();
        when(candidateService.createCandidate(any())).thenReturn(Entities.getCandidateJunior());

        mockMvc.perform(MockMvcRequestBuilders.post("/candidate/new")
                        .flashAttr("candidate", candidateDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/candidate"));
    }

    @Test
    @WithMockUser(username = "candidate@example.com", roles = {"CANDIDATE"})
    public void testShowCandidateProfile() throws Exception {
        String candidateEmail = "candidate@example.com";
        Candidate candidate = Entities.getCandidateJunior();
        when(candidateService.findCandidateByEmail(candidateEmail)).thenReturn(candidate);
        when(userService.findRoleByMail(anyString())).thenReturn("COMPANY");

        mockMvc.perform(MockMvcRequestBuilders.get("/candidate/profile/" + candidateEmail)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("candidate-profile"));
    }

    @Test
    @WithMockUser(username = "candidate@example.com", roles = {"CANDIDATE"})
    public void testHireCandidate() throws Exception {
        String candidateEmail = "candidate@example.com";
        Candidate candidate = Entities.getCandidateJunior();
        when(candidateService.findCandidateByEmail(candidateEmail)).thenReturn(candidate);

        mockMvc.perform(MockMvcRequestBuilders.post("/hire/" + candidateEmail)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/candidate"));
    }

    @Test
    @WithMockUser(username = "candidate@example.com", roles = {"CANDIDATE"})
    public void testGetCandidateProfileImage() throws Exception {
        String candidateEmail = "candidate@example.com";
        byte[] profileImage = "test-image".getBytes();
        Candidate candidate = Entities.getCandidateJunior().withProfileImage(profileImage);

        when(candidateService.findCandidateByEmail(candidateEmail)).thenReturn(candidate);

        mockMvc.perform(MockMvcRequestBuilders.get("/candidate/profile/image/" + candidateEmail)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}