package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.SkillsDTO;
import com.itjobapp.Service.domain.Skills;
import com.itjobapp.configuration.PersistenceContainerTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
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
