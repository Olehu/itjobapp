package com.itjobapp.Database.repository.mapper;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandidateEntityMapperTest {

    @InjectMocks
    private CandidateEntityMapper candidateEntityMapper = Mappers.getMapper(CandidateEntityMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMapToEntity() {
        Candidate candidate = Entities.getCandidateJunior();

        CandidateEntity candidateEntity = candidateEntityMapper.mapToEntity(candidate);

        assertEquals(candidate.getFirstName(), candidateEntity.getFirstName());
        assertEquals(candidate.getLastName(), candidateEntity.getLastName());
        assertEquals(candidate.getEmail(), candidateEntity.getEmail());
        assertEquals(candidate.getPhoneNumber(), candidateEntity.getPhoneNumber());
        assertEquals(candidate.getExperiencelevel(), candidateEntity.getExperiencelevel());
        assertEquals(candidate.getAvailabilityStatus(), candidateEntity.getAvailabilityStatus());
        assertEquals(candidate.getDescription(), candidateEntity.getDescription());
        assertEquals(candidate.getProfileImage(), candidateEntity.getProfileImage());
    }

    @Test
    public void testMapFromEntity() {
        CandidateEntity candidateEntity = Entities.getCandidateEntityJunior();

        Candidate candidate = candidateEntityMapper.mapFromEntity(candidateEntity);

        assertEquals(candidateEntity.getFirstName(), candidate.getFirstName());
        assertEquals(candidateEntity.getLastName(), candidate.getLastName());
        assertEquals(candidateEntity.getEmail(), candidate.getEmail());
        assertEquals(candidateEntity.getPhoneNumber(), candidate.getPhoneNumber());
        assertEquals(candidateEntity.getExperiencelevel(), candidate.getExperiencelevel());
        assertEquals(candidateEntity.getAvailabilityStatus(), candidate.getAvailabilityStatus());
        assertEquals(candidateEntity.getDescription(), candidate.getDescription());
        assertEquals(candidateEntity.getProfileImage(), candidate.getProfileImage());
    }
}
