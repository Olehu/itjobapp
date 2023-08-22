package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.SkillsDTO;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Service.domain.Skills;
import com.itjobapp.configuration.PersistenceContainerTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
public class CompanyMapperImplTest {

    @Autowired
    private CompanyMapperImpl companyMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSkillsToSkillsDTO() {
        Skills skills = Skills.builder().skillName("Java").build();

        SkillsDTO skillsDTO = companyMapper.skillsToSkillsDTO(skills);

        assertNotNull(skillsDTO);
        assertEquals(skills.getSkillName(), skillsDTO.getSkillName());
    }

    @Test
    public void testSkillsSetToSkillsDTOSet() {
        Set<Skills> skillsSet = new HashSet<>();
        skillsSet.add(Skills.builder().skillName("Java").build());
        skillsSet.add(Skills.builder().skillName("Python").build());

        Set<SkillsDTO> skillsDTOSet = companyMapper.skillsSetToSkillsDTOSet(skillsSet);

        assertNotNull(skillsDTOSet);
        assertEquals(skillsSet.size(), skillsDTOSet.size());
        skillsDTOSet.forEach(skillsDTO -> {
            assertTrue(skillsSet.stream().anyMatch(skills -> skills.getSkillName().equals(skillsDTO.getSkillName())));
        });
    }

    @Test
    public void testJobOfferToJobOfferDTO() {
        Set<Skills> skillsSet = new HashSet<>();
        skillsSet.add(Skills.builder().skillName("Java").build());
        skillsSet.add(Skills.builder().skillName("Python").build());

        Company company = Company.builder()
                .companyName("TestCompany")
                .industry("IT")
                .city("TestCity")
                .email("test@example.com")
                .isHiring(true)
                .description("TestDescription")
                .build();

        JobOffer jobOffer = JobOffer.builder()
                .name("TestJob")
                .experienceLevel("Senior")
                .otherRequirements("TestRequirements")
                .remote("Remote")
                .skills(skillsSet)
                .company(company)
                .build();

        JobOfferDTO jobOfferDTO = companyMapper.jobOfferToJobOfferDTO(jobOffer);

        assertNotNull(jobOfferDTO);
        assertEquals(jobOffer.getName(), jobOfferDTO.getName());
        assertEquals(jobOffer.getExperienceLevel(), jobOfferDTO.getExperienceLevel());
        assertEquals(jobOffer.getOtherRequirements(), jobOfferDTO.getOtherRequirements());
        assertEquals(jobOffer.getRemote(), jobOfferDTO.getRemote());
        assertEquals(jobOffer.getSkills().size(), jobOfferDTO.getSkills().size());
    }

    @Test
    public void testJobOfferSetToJobOfferDTOSet() {
        Set<Skills> skillsSet = new HashSet<>();
        skillsSet.add(Skills.builder().skillName("Java").build());
        skillsSet.add(Skills.builder().skillName("Python").build());

        Company company = Company.builder()
                .companyName("TestCompany")
                .industry("IT")
                .city("TestCity")
                .email("test@example.com")
                .isHiring(true)
                .description("TestDescription")
                .build();

        Set<JobOffer> jobOfferSet = new HashSet<>();
        jobOfferSet.add(JobOffer.builder()
                .name("TestJob1")
                .experienceLevel("Senior")
                .otherRequirements("TestRequirements1")
                .remote("Remote")
                .skills(skillsSet)
                .company(company)
                .build());
        jobOfferSet.add(JobOffer.builder()
                .name("TestJob2")
                .experienceLevel("Junior")
                .otherRequirements("TestRequirements2")
                .remote("Onsite")
                .skills(skillsSet)
                .company(company)
                .build());

        Set<JobOfferDTO> jobOfferDTOSet = companyMapper.jobOfferSetToJobOfferDTOSet(jobOfferSet);

        assertNotNull(jobOfferDTOSet);
        assertEquals(jobOfferSet.size(), jobOfferDTOSet.size());
        jobOfferDTOSet.forEach(jobOfferDTO -> {
            assertTrue(jobOfferSet.stream().anyMatch(jobOffer -> jobOffer.getName().equals(jobOfferDTO.getName())));
        });
    }

}
