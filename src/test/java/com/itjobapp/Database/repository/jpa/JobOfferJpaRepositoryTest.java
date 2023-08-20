package com.itjobapp.Database.repository.jpa;

import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;

import java.util.HashSet;
import java.util.Optional;

import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {JobOfferJpaRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.itjobapp.Database.entity"})
@DataJpaTest
class JobOfferJpaRepositoryTest {
    @Autowired
    private JobOfferJpaRepository jobOfferJpaRepository;

    @Test
    public void testCreateJobOffer() {
        JobOfferEntity jobOfferEntity = Entities.jobOfferentityMid();

        jobOfferJpaRepository.save(jobOfferEntity);
        Optional<JobOfferEntity> found = jobOfferJpaRepository.findByName(jobOfferEntity.getName());

        assertTrue(found.isPresent());
        assertEquals(jobOfferEntity.getName(), found.get().getName());
    }
}

