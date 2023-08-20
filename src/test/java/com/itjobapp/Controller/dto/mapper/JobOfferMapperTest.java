package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobOfferMapperTest {

    @InjectMocks
    private JobOfferMapper jobOfferMapper = Mappers.getMapper(JobOfferMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMap() {
        JobOffer jobOffer = Entities.jobOfferJunior().withSkills(Collections.emptySet());

        JobOfferDTO jobOfferDTO = jobOfferMapper.map(jobOffer);

        assertEquals(jobOffer.getName(), jobOfferDTO.getName());
        assertEquals(jobOffer.getExperienceLevel(), jobOfferDTO.getExperienceLevel());
        assertEquals(jobOffer.getOtherRequirements(), jobOfferDTO.getOtherRequirements());
        assertEquals(jobOffer.getRemote(), jobOfferDTO.getRemote());
        assertEquals(jobOffer.getSkills(), jobOfferDTO.getSkills());
    }

    @Test
    public void testMaper() {
        JobOfferDTO jobOfferDTO = Entities.jobOfferDTOJunior().withSkills(Collections.emptySet());

        JobOffer jobOffer = jobOfferMapper.maper(jobOfferDTO);

        assertEquals(jobOfferDTO.getName(), jobOffer.getName());
        assertEquals(jobOfferDTO.getExperienceLevel(), jobOffer.getExperienceLevel());
        assertEquals(jobOfferDTO.getOtherRequirements(), jobOffer.getOtherRequirements());
        assertEquals(jobOfferDTO.getRemote(), jobOffer.getRemote());
        assertEquals(jobOfferDTO.getSkills(), jobOffer.getSkills());
    }
}
