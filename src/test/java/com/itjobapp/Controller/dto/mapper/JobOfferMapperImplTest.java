package com.itjobapp.Controller.dto.mapper;


import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.SkillsDTO;
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
public class JobOfferMapperImplTest {

    @Autowired
    private JobOfferMapperImpl jobOfferMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSkillsToSkillsDTO() {
        Skills skills = Skills.builder().skillName("Java").build();

        SkillsDTO skillsDTO = jobOfferMapper.skillsToSkillsDTO(skills);

        assertNotNull(skillsDTO);
        assertEquals(skills.getSkillName(), skillsDTO.getSkillName());
    }

    @Test
    public void testSkillsDTOToSkills() {
        SkillsDTO skillsDTO = SkillsDTO.builder().skillName("Java").build();

        Skills skills = jobOfferMapper.skillsDTOToSkills(skillsDTO);

        assertNotNull(skills);
        assertEquals(skillsDTO.getSkillName(), skills.getSkillName());
    }


    @Test
    public void testSkillsDTOSetToSkillsSet() {
        SkillsDTO skillsDTO1 = SkillsDTO.builder().skillName("Java").build();
        SkillsDTO skillsDTO2 = SkillsDTO.builder().skillName("Spring").build();
        Set<SkillsDTO> skillsDTOSet = new HashSet<>();
        skillsDTOSet.add(skillsDTO1);
        skillsDTOSet.add(skillsDTO2);

        Set<Skills> skillsSet = jobOfferMapper.skillsDTOSetToSkillsSet(skillsDTOSet);

        assertNotNull(skillsSet);
        assertEquals(2, skillsSet.size());
        assertTrue(skillsSet.stream().anyMatch(skill -> skill.getSkillName().equals("Java")));
        assertTrue(skillsSet.stream().anyMatch(skill -> skill.getSkillName().equals("Spring")));
    }

    @Test
    public void testJobOfferDTOToJobOffer() {
        JobOfferDTO jobOfferDTO = new JobOfferDTO();
        jobOfferDTO.setName("Java Developer");
        jobOfferDTO.setExperienceLevel("Mid");
        jobOfferDTO.setOtherRequirements("Spring, Hibernate");
        jobOfferDTO.setRemote("Remote");
        jobOfferDTO.setSkills(new HashSet<>());

        JobOffer jobOffer = jobOfferMapper.maper(jobOfferDTO);

        assertNotNull(jobOffer);
        assertEquals(jobOfferDTO.getName(), jobOffer.getName());
        assertEquals(jobOfferDTO.getExperienceLevel(), jobOffer.getExperienceLevel());
        assertEquals(jobOfferDTO.getOtherRequirements(), jobOffer.getOtherRequirements());
        assertEquals(jobOfferDTO.getRemote(), jobOffer.getRemote());
        assertEquals(jobOfferDTO.getSkills().size(), jobOffer.getSkills().size());
    }
}
