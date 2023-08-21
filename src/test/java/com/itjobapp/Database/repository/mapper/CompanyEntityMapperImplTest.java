package com.itjobapp.Database.repository.mapper;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.SkillsDTO;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.entity.SkillsEntity;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Service.domain.Skills;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CompanyEntityMapperImplTest {

    @Autowired
    private CompanyEntityMapperImpl companyEntityMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSkillsToSkillsEntity() {
        Skills skills = Skills.builder().skillName("Java").build();

        SkillsEntity skillsEntity = companyEntityMapper.skillsToSkillsEntity(skills);

        assertNotNull(skillsEntity);
        assertEquals(skills.getSkillName(), skillsEntity.getSkillName());
    }

    @Test
    public void testSkillsSetToSkillsEntitySet() {
        Set<Skills> skillsSet = new HashSet<>();
        skillsSet.add(Skills.builder().skillName("Java").build());
        skillsSet.add(Skills.builder().skillName("Python").build());

        Set<SkillsEntity> skillsEntitySet = companyEntityMapper.skillsSetToSkillsEntitySet(skillsSet);

        assertNotNull(skillsEntitySet);
        skillsEntitySet.forEach(skillsEntity -> {
            assertTrue(skillsSet.stream().anyMatch(skills -> skills.getSkillName().equals(skillsEntity.getSkillName())));
        });
    }

    @Test
    public void testJobOfferToJobOfferEntity() {
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

        JobOfferEntity jobOfferEntity = companyEntityMapper.jobOfferToJobOfferEntity(jobOffer);

        assertNotNull(jobOfferEntity);
        assertEquals(jobOffer.getName(), jobOfferEntity.getName());
        assertEquals(jobOffer.getExperienceLevel(), jobOfferEntity.getExperienceLevel());
        assertEquals(jobOffer.getOtherRequirements(), jobOfferEntity.getOtherRequirements());
        assertEquals(jobOffer.getRemote(), jobOfferEntity.getRemote());
    }
    @Test
    public void testSkillsDTOToSkills() {
        SkillsDTO skillsDTO = new SkillsDTO();
        skillsDTO.setSkillName("Java");

        Skills result = new Skills("Java");
        assertNotNull(result);
        assertEquals("Java", result.getSkillName());
    }

    @Test
    public void testSkillsDTOSetToSkillsSet() {
        Set<SkillsDTO> skillsDTOSet = new HashSet<>();
        SkillsDTO skillsDTO1 = new SkillsDTO();
        skillsDTO1.setSkillName("Java");
        skillsDTOSet.add(skillsDTO1);



        assertEquals(skillsDTOSet.size(), 1);
    }

    @Test
    public void testJobOfferDTOToJobOffer() {
        SkillsDTO skillsDTO = new SkillsDTO();
        skillsDTO.setSkillName("Java");
        Set<SkillsDTO> skillsDTOSet = new HashSet<>();
        skillsDTOSet.add(skillsDTO);


        JobOfferDTO jobOfferDTO = Entities.jobOfferDTOJunior();


        JobOffer jobOffer = Entities.jobOfferJunior();

        assertNotNull(jobOffer);
        assertEquals(jobOfferDTO.getName(), jobOffer.getName());
        assertEquals(jobOfferDTO.getExperienceLevel(), jobOffer.getExperienceLevel());
        assertEquals(jobOfferDTO.getOtherRequirements(), jobOffer.getOtherRequirements());
        assertEquals(jobOfferDTO.getRemote(), jobOffer.getRemote());
    }

}
