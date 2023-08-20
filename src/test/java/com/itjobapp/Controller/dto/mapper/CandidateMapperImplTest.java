package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.SkillsDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapperImpl;
import com.itjobapp.Service.domain.Skills;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CandidateMapperImplTest {

    @Autowired
    private CandidateMapperImpl candidateMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSkillsToSkillsDTO() {
        Skills skills = Skills.builder().skillName("Java").build();

        SkillsDTO skillsDTO = candidateMapper.skillsToSkillsDTO(skills);

        assertNotNull(skillsDTO);
        assertEquals(skills.getSkillName(), skillsDTO.getSkillName());
    }

    @Test
    public void testSkillsDTOToSkills() {
        SkillsDTO skillsDTO = SkillsDTO.builder().skillName("Java").build();

        Skills skills = candidateMapper.skillsDTOToSkills(skillsDTO);

        assertNotNull(skills);
        assertEquals(skillsDTO.getSkillName(), skills.getSkillName());
    }
}
