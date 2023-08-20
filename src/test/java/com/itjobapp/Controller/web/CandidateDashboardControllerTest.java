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
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CandidateDashboardController.class)
public class CandidateDashboardControllerTest {

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
    public void testEditProfile() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("candidate@example.com");

        Candidate existingCandidate = Entities.getCandidateJunior();
        CandidateDTO candidateDTO = Entities.getCandidateDTOJunior();

        when(candidateService.findCandidateByEmail("candidate@example.com")).thenReturn(existingCandidate);
        when(candidateMapper.maper(candidateDTO)).thenReturn(existingCandidate);

        mockMvc.perform(MockMvcRequestBuilders.post("/dashboard/candidate-edit-profile")
                        .principal(authentication)
                        .flashAttr("candidateDTO", candidateDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }

    @Test
    @WithMockUser(username = "candidate@example.com", roles = {"CANDIDATE"})
    public void testUploadImageForm() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("candidate@example.com");

        CandidateDTO candidateDTO = Entities.getCandidateDTOJunior();

        when(candidateService.findCandidateByEmail("candidate@example.com")).thenReturn(Entities.getCandidateJunior());
        when(candidateMapper.map(Entities.getCandidateJunior())).thenReturn(candidateDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/upload-image")
                        .principal(authentication))
                .andExpect(status().isOk())
                .andExpect(view().name("upload-image"))
                .andExpect(model().attribute("candidate", candidateDTO));
    }

    @Test
    @WithMockUser(username = "candidate@example.com", roles = {"CANDIDATE"})
    public void testUploadProfileImage() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("candidate@example.com");

        MultipartFile imageFile = mock(MultipartFile.class);

        when(candidateService.findCandidateByEmail("candidate@example.com")).thenReturn(Entities.getCandidateJunior());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload-image")
                        .file("image", imageFile.getBytes())
                        .principal(authentication)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }


}

