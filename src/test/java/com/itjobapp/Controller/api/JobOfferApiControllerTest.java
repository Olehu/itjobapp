package com.itjobapp.Controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.CompanyMapperImpl;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapperImpl;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.repository.CompanyRepository;
import com.itjobapp.Database.repository.JobOfferRepository;
import com.itjobapp.Database.repository.jpa.CompanyJpaRepository;
import com.itjobapp.Database.repository.jpa.JobOfferJpaRepository;
import com.itjobapp.Database.repository.jpa.SkillsJpaRepository;
import com.itjobapp.Database.repository.mapper.CompanyEntityMapperImpl;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapperImpl;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.JobOfferService;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Service.domain.Skills;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {JobOfferApiController.class})
@ExtendWith(SpringExtension.class)
class JobOfferApiControllerTest {
    @MockBean
    private CompanyMapper companyMapper;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private JobOfferApiController jobOfferApiController;

    @MockBean
    private JobOfferMapper jobOfferMapper;

    @MockBean
    private JobOfferService jobOfferService;

    /**
     * Method under test: {@link JobOfferApiController#createJobOffer(JobOfferDTO, String)}
     */
    @Test
    void testCreateJobOffer() throws Exception {
        when(jobOfferService.getAllJobOffer()).thenReturn(new ArrayList<>());

        JobOfferDTO jobOfferDTO = new JobOfferDTO();
        jobOfferDTO.setCompany(new CompanyDTO());
        jobOfferDTO.setExperienceLevel("Experience Level");
        jobOfferDTO.setName("Name");
        jobOfferDTO.setOtherRequirements("Other Requirements");
        jobOfferDTO.setRemote("Remote");
        jobOfferDTO.setSkills(new HashSet<>());
        String content = (new ObjectMapper()).writeValueAsString(jobOfferDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/joboffers")
                .param("companyName", "foo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jobOfferApiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobOfferApiController#getJobOffers(String, String, Set)}
     */
    @Test
    void testGetJobOffers() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        JobOfferJpaRepository jobOfferJpaRepository = mock(JobOfferJpaRepository.class);
        when(jobOfferJpaRepository.findAll()).thenReturn(new ArrayList<>());
        JobOfferEntityMapperImpl jobOfferEntityMapper = new JobOfferEntityMapperImpl();
        CompanyJpaRepository companyJpaRepository = mock(CompanyJpaRepository.class);
        JobOfferRepository jobOfferDao = new JobOfferRepository(jobOfferEntityMapper, jobOfferJpaRepository,
                companyJpaRepository, new CompanyEntityMapperImpl(), mock(SkillsJpaRepository.class));

        JobOfferService jobOfferService = new JobOfferService(jobOfferDao, new JobOfferMapperImpl());

        JobOfferMapperImpl jobOfferMapper = new JobOfferMapperImpl();
        CompanyEntityMapperImpl companyEntityMapper = new CompanyEntityMapperImpl();
        CompanyJpaRepository companyJpaRepository2 = mock(CompanyJpaRepository.class);
        CompanyService companyService = new CompanyService(
                new CompanyRepository(companyEntityMapper, companyJpaRepository2, new JobOfferEntityMapperImpl()));
        JobOfferApiController jobOfferApiController = new JobOfferApiController(jobOfferService, jobOfferMapper,
                companyService, new CompanyMapperImpl());
        ResponseEntity<List<JobOfferDTO>> actualJobOffers = jobOfferApiController.getJobOffers("Experience Level",
                "Remote", new HashSet<>());
        assertTrue(actualJobOffers.hasBody());
        assertEquals(200, actualJobOffers.getStatusCodeValue());
        assertTrue(actualJobOffers.getHeaders().isEmpty());
        verify(jobOfferJpaRepository).findAll();
    }

    /**
     * Method under test: {@link JobOfferApiController#getJobOffers(String, String, Set)}
     */
    @Test
    void testGetJobOffers2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        CompanyEntity company = new CompanyEntity();
        company.setCity("Oxford");
        company.setCompanyId(1);
        company.setCompanyName("Company Name");
        company.setDescription("The characteristics of someone or something");
        company.setEmail("jane.doe@example.org");
        company.setIndustry("Industry");
        company.setIsHiring(true);
        company.setJobOffers(new HashSet<>());

        JobOfferEntity jobOfferEntity = new JobOfferEntity();
        jobOfferEntity.setCompany(company);
        jobOfferEntity.setExperienceLevel("Experience Level");
        jobOfferEntity.setJobOfferId(1);
        jobOfferEntity.setName("Name");
        jobOfferEntity.setOtherRequirements("Other Requirements");
        jobOfferEntity.setRemote("Remote");
        jobOfferEntity.setSkills(new HashSet<>());

        ArrayList<JobOfferEntity> jobOfferEntityList = new ArrayList<>();
        jobOfferEntityList.add(jobOfferEntity);
        JobOfferJpaRepository jobOfferJpaRepository = mock(JobOfferJpaRepository.class);
        when(jobOfferJpaRepository.findAll()).thenReturn(jobOfferEntityList);
        JobOfferEntityMapperImpl jobOfferEntityMapper = new JobOfferEntityMapperImpl();
        CompanyJpaRepository companyJpaRepository = mock(CompanyJpaRepository.class);
        JobOfferRepository jobOfferDao = new JobOfferRepository(jobOfferEntityMapper, jobOfferJpaRepository,
                companyJpaRepository, new CompanyEntityMapperImpl(), mock(SkillsJpaRepository.class));

        JobOfferService jobOfferService = new JobOfferService(jobOfferDao, new JobOfferMapperImpl());

        JobOfferMapperImpl jobOfferMapper = new JobOfferMapperImpl();
        CompanyEntityMapperImpl companyEntityMapper = new CompanyEntityMapperImpl();
        CompanyJpaRepository companyJpaRepository2 = mock(CompanyJpaRepository.class);
        CompanyService companyService = new CompanyService(
                new CompanyRepository(companyEntityMapper, companyJpaRepository2, new JobOfferEntityMapperImpl()));
        JobOfferApiController jobOfferApiController = new JobOfferApiController(jobOfferService, jobOfferMapper,
                companyService, new CompanyMapperImpl());
        ResponseEntity<List<JobOfferDTO>> actualJobOffers = jobOfferApiController.getJobOffers("Experience Level",
                "Remote", new HashSet<>());
        assertTrue(actualJobOffers.hasBody());
        assertEquals(200, actualJobOffers.getStatusCodeValue());
        assertTrue(actualJobOffers.getHeaders().isEmpty());
        verify(jobOfferJpaRepository).findAll();
    }

    /**
     * Method under test: {@link JobOfferApiController#getJobOffers(String, String, Set)}
     */
    @Test
    void testGetJobOffers3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        CompanyEntity company = new CompanyEntity();
        company.setCity("Oxford");
        company.setCompanyId(1);
        company.setCompanyName("Company Name");
        company.setDescription("The characteristics of someone or something");
        company.setEmail("jane.doe@example.org");
        company.setIndustry("Industry");
        company.setIsHiring(true);
        company.setJobOffers(new HashSet<>());

        JobOfferEntity jobOfferEntity = new JobOfferEntity();
        jobOfferEntity.setCompany(company);
        jobOfferEntity.setExperienceLevel("Experience Level");
        jobOfferEntity.setJobOfferId(1);
        jobOfferEntity.setName("Name");
        jobOfferEntity.setOtherRequirements("Other Requirements");
        jobOfferEntity.setRemote("Remote");
        jobOfferEntity.setSkills(new HashSet<>());

        CompanyEntity company2 = new CompanyEntity();
        company2.setCity("London");
        company2.setCompanyId(2);
        company2.setCompanyName("com.itjobapp.Database.entity.CompanyEntity");
        company2.setDescription("Description");
        company2.setEmail("john.smith@example.org");
        company2.setIndustry("com.itjobapp.Database.entity.CompanyEntity");
        company2.setIsHiring(false);
        company2.setJobOffers(new HashSet<>());

        JobOfferEntity jobOfferEntity2 = new JobOfferEntity();
        jobOfferEntity2.setCompany(company2);
        jobOfferEntity2.setExperienceLevel("com.itjobapp.Database.entity.JobOfferEntity");
        jobOfferEntity2.setJobOfferId(2);
        jobOfferEntity2.setName("com.itjobapp.Database.entity.JobOfferEntity");
        jobOfferEntity2.setOtherRequirements("com.itjobapp.Database.entity.JobOfferEntity");
        jobOfferEntity2.setRemote("com.itjobapp.Database.entity.JobOfferEntity");
        jobOfferEntity2.setSkills(new HashSet<>());

        ArrayList<JobOfferEntity> jobOfferEntityList = new ArrayList<>();
        jobOfferEntityList.add(jobOfferEntity2);
        jobOfferEntityList.add(jobOfferEntity);
        JobOfferJpaRepository jobOfferJpaRepository = mock(JobOfferJpaRepository.class);
        when(jobOfferJpaRepository.findAll()).thenReturn(jobOfferEntityList);
        JobOfferEntityMapperImpl jobOfferEntityMapper = new JobOfferEntityMapperImpl();
        CompanyJpaRepository companyJpaRepository = mock(CompanyJpaRepository.class);
        JobOfferRepository jobOfferDao = new JobOfferRepository(jobOfferEntityMapper, jobOfferJpaRepository,
                companyJpaRepository, new CompanyEntityMapperImpl(), mock(SkillsJpaRepository.class));

        JobOfferService jobOfferService = new JobOfferService(jobOfferDao, new JobOfferMapperImpl());

        JobOfferMapperImpl jobOfferMapper = new JobOfferMapperImpl();
        CompanyEntityMapperImpl companyEntityMapper = new CompanyEntityMapperImpl();
        CompanyJpaRepository companyJpaRepository2 = mock(CompanyJpaRepository.class);
        CompanyService companyService = new CompanyService(
                new CompanyRepository(companyEntityMapper, companyJpaRepository2, new JobOfferEntityMapperImpl()));
        JobOfferApiController jobOfferApiController = new JobOfferApiController(jobOfferService, jobOfferMapper,
                companyService, new CompanyMapperImpl());
        ResponseEntity<List<JobOfferDTO>> actualJobOffers = jobOfferApiController.getJobOffers("Experience Level",
                "Remote", new HashSet<>());
        assertTrue(actualJobOffers.hasBody());
        assertEquals(200, actualJobOffers.getStatusCodeValue());
        assertTrue(actualJobOffers.getHeaders().isEmpty());
        verify(jobOfferJpaRepository).findAll();
    }

    /**
     * Method under test: {@link JobOfferApiController#getJobOffers(String, String, Set)}
     */

    @Test
    void testGetJobOfferProfile() throws Exception {
        HashSet<Skills> skills = new HashSet<>();
        when(jobOfferService.getJobOfferByName(Mockito.<String>any()))
                .thenReturn(new JobOffer("Name", "Experience Level", "Other Requirements", "Remote", skills, new Company()));
        when(jobOfferMapper.map(Mockito.<JobOffer>any())).thenReturn(new JobOfferDTO());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/joboffers/{name}", "Name");
        MockMvcBuilders.standaloneSetup(jobOfferApiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":null,\"experienceLevel\":null,\"otherRequirements\":null,\"remote\":null,\"company\":null,\"skills"
                                        + "\":null}"));
    }

    /**
     * Method under test: {@link JobOfferApiController#getJobOfferProfile(String)}
     */
    @Test
    void testGetJobOfferProfile2() throws Exception {
        HashSet<Skills> skills = new HashSet<>();
        when(jobOfferService.getJobOfferByName(Mockito.<String>any()))
                .thenReturn(new JobOffer("Name", "Experience Level", "Other Requirements", "Remote", skills, new Company()));
        when(jobOfferMapper.map(Mockito.<JobOffer>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/joboffers/{name}", "Name");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jobOfferApiController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

