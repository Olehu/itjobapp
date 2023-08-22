package com.itjobapp.Controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Service.CandidateService;
import com.itjobapp.Util.Entities;
import com.itjobapp.configuration.PersistenceContainerTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
public class CandidateApiControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CandidateService candidateService;

    @Mock
    private CandidateMapper candidateMapper;

    @InjectMocks
    private CandidateApiController candidateApiController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(candidateApiController).build();
    }

    @Test
    public void testGetCandidates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/candidates"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testCreateCandidate() throws Exception {
        CandidateDTO candidateDTO = Entities.getCandidateDTOJunior();

        when(candidateMapper.maper(candidateDTO)).thenReturn(Entities.getCandidateJunior());

        ObjectMapper objectMapper = new ObjectMapper();
        String candidateJson = objectMapper.writeValueAsString(candidateDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/candidates")
                        .contentType("application/json")
                        .content(candidateJson))
                .andExpect(status().isOk());
    }


}
