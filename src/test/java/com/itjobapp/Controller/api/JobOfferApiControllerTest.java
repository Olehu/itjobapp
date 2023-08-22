package com.itjobapp.Controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.JobOfferService;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
public class JobOfferApiControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JobOfferService jobOfferService;

    @Mock
    private JobOfferMapper jobOfferMapper;

    @Mock
    private CompanyService companyService;

    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private JobOfferApiController jobOfferApiController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jobOfferApiController).build();
    }

    @Test
    public void testGetJobOffers() throws Exception {
        JobOffer jobOffer = Entities.jobOfferJunior();
        JobOffer jobOffer2 = Entities.jobOfferJunior().withName("second");

        List<JobOffer> jobOffers = List.of(jobOffer, jobOffer2);

        when(jobOfferService.getAllJobOffer()).thenReturn(jobOffers);
        when(jobOfferMapper.map(any())).thenReturn(Entities.jobOfferDTOJunior());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/joboffers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testCreateJobOffer() throws Exception {
        JobOfferDTO jobOfferDTO = Entities.jobOfferDTOJunior();
        Company company = Entities.getCompanyABC();

        when(companyService.getCompanyByName(any())).thenReturn(company);
        when(companyMapper.map(any())).thenReturn(Entities.getCompanyDTOABC());
        when(jobOfferMapper.maper(any())).thenReturn(Entities.jobOfferJunior());

        ObjectMapper objectMapper = new ObjectMapper();
        String jobOfferJson = objectMapper.writeValueAsString(jobOfferDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/joboffers")
                        .param("companyName", "Test Company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jobOfferJson))
                .andExpect(status().isOk());
    }


}
