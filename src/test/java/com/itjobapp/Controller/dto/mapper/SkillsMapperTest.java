package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.SkillsDTO;
import com.itjobapp.Service.domain.Skills;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkillsMapperTest {

    @InjectMocks
    private SkillsMapper skillsMapper = Mappers.getMapper(SkillsMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMap() {
        Skills skill = Skills.builder()
                .skillName("Java")
                .build();

        SkillsDTO skillsDTO = skillsMapper.map(skill);

        assertEquals(skill.getSkillName(), skillsDTO.getSkillName());
    }

    @Test
    public void testMaper() {
        SkillsDTO skillsDTO = SkillsDTO.builder()
                .skillName("Java")
                .build();

        Skills skill = skillsMapper.maper(skillsDTO);

        assertEquals(skillsDTO.getSkillName(), skill.getSkillName());
    }
}
