package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandidateMapperTest {

    @InjectMocks
    private CandidateMapper candidateMapper = Mappers.getMapper(CandidateMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMap() {
        Candidate candidate = Entities.getCandidateJunior().withSkills(Collections.emptySet());
        CandidateDTO candidateDTO = candidateMapper.map(candidate);

        assertEquals(candidate.getFirstName(), candidateDTO.getFirstName());
        assertEquals(candidate.getLastName(), candidateDTO.getLastName());
        assertEquals(candidate.getEmail(), candidateDTO.getEmail());
        assertEquals(candidate.getPhoneNumber(), candidateDTO.getPhoneNumber());
        assertEquals(candidate.getExperiencelevel(), candidateDTO.getExperiencelevel());
        assertEquals(candidate.getAvailabilityStatus(), candidateDTO.getAvailabilityStatus());
        assertEquals(candidate.getDescription(), candidateDTO.getDescription());
        assertEquals(candidate.getProfileImage(), candidateDTO.getProfileImage());
        assertEquals(candidate.getSkills(), candidateDTO.getSkills());
    }

    @Test
    public void testMaper() {
        CandidateDTO candidateDTO = Entities.getCandidateDTOJunior().withSkills(Collections.emptySet());

        Candidate candidate = candidateMapper.maper(candidateDTO);

        assertEquals(candidateDTO.getFirstName(), candidate.getFirstName());
        assertEquals(candidateDTO.getLastName(), candidate.getLastName());
        assertEquals(candidateDTO.getEmail(), candidate.getEmail());
        assertEquals(candidateDTO.getPhoneNumber(), candidate.getPhoneNumber());
        assertEquals(candidateDTO.getExperiencelevel(), candidate.getExperiencelevel());
        assertEquals(candidateDTO.getAvailabilityStatus(), candidate.getAvailabilityStatus());
        assertEquals(candidateDTO.getDescription(), candidate.getDescription());
        assertEquals(candidateDTO.getProfileImage(), candidate.getProfileImage());
        assertEquals(candidateDTO.getSkills(), candidate.getSkills());
    }
}