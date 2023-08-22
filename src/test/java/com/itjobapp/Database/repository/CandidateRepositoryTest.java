package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Util.Entities;
import com.itjobapp.configuration.PersistenceContainerTestConfiguration;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
public class CandidateRepositoryTest {
    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    @Transactional
    public void testCreateCandidateAndFindByName() {
        Candidate candidate = Entities.getCandidateJunior();
        CandidateEntity candidateEntity = Entities.getCandidateEntityJunior();

        Candidate candidateCreated = candidateRepository.create(candidate);
        Candidate found = candidateRepository.findByEmail(candidateEntity.getEmail()).get();

        assertEquals(candidate, candidateCreated);
        assertEquals(candidate, found);
    }

    @Test
    @Transactional
    public void testcreateByMailGetAllAndUpdateCandidate() {
        Candidate candidate = Entities.getCandidateMid();

        Candidate candidateByMail = candidateRepository.createByMail("testemail@gmail.com");
        candidateRepository.create(candidate);

        List<Candidate> allCandidates = candidateRepository.getAllCandidates();

        Candidate updated = candidateRepository.update(candidate.withFirstName("New Name"));


        assertEquals(2, allCandidates.size());
        assertEquals(updated, candidate.withFirstName("New Name"));
        assertNotEquals(candidate, updated);
        assertEquals(null,candidateByMail.getDescription());
        assertEquals(null,candidateByMail.getSkills());
        assertEquals(false,candidateByMail.getAvailabilityStatus());
        assertEquals(null,candidateByMail.getFirstName());
        assertEquals(null,candidateByMail.getLastName());
        assertEquals(null,candidateByMail.getPhoneNumber());
        assertEquals(null,candidateByMail.getProfileImage());
    }

}

