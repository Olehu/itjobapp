package com.itjobapp.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.itjobapp.Controller.web.exception.NotFoundException;
import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.repository.mapper.CandidateEntityMapper;
import com.itjobapp.Service.dao.CandidateDAO;
import com.itjobapp.Service.domain.Candidate;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.io.UnsupportedEncodingException;

import java.util.*;

import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class CandidateServiceTest {

    @Mock
    private CandidateDAO candidateDAO;

    @Mock
    private CandidateEntityMapper candidateEntityMapper;

    @InjectMocks
    private CandidateService candidateService;

    @Test
    @DisplayName("Should get all candidates")
    void getAllCandidates() {
        List<Candidate> expectedCandidates = List.of(Entities.getCandidateJunior(), Entities.getCandidateMid());

        when(candidateDAO.getAllCandidates()).thenReturn(expectedCandidates);

        List<Candidate> result = candidateService.getAllCandidates();

        assertEquals(expectedCandidates, result);
        verify(candidateDAO).getAllCandidates();
    }

    @Test
    @DisplayName("Should find candidate by email")
    void findCandidateByEmail() {
        String email = "test@example.com";
        Candidate expectedCandidate = Entities.getCandidateJunior().withEmail(email);

        when(candidateDAO.findByEmail(email)).thenReturn(Optional.of(expectedCandidate));

        Candidate result = candidateService.findCandidateByEmail(email);

        assertEquals(expectedCandidate, result);
        verify(candidateDAO).findByEmail(email);
    }


    @Test
    @DisplayName("Should create candidate")
    void createCandidate() {
        Candidate candidate = Entities.getCandidateJunior();

        when(candidateDAO.create(candidate)).thenReturn(candidate);

        Candidate result = candidateService.createCandidate(candidate);

        assertEquals(candidate, result);
        verify(candidateDAO).create(candidate);
    }

    @Test
    @DisplayName("Should update candidate")
    void updateCandidate() {
        Candidate candidate = Entities.getCandidateJunior();


        when(candidateDAO.update(candidate)).thenReturn(candidate);

        Candidate result = candidateService.update(candidate);

        assertEquals(candidate, result);
        verify(candidateDAO).update(candidate);
    }



    @Test
    @DisplayName("Should search Candidate")
    void searchCandidates() {
        List<Candidate> expectedCandidates = List.of(Entities.getCandidateJunior(), Entities.getCandidateMid());

        when(candidateDAO.getAllCandidates()).thenReturn(expectedCandidates);

        List<Candidate> result = candidateService.searchCandidates(true,  Set.of("Java"));


        assertEquals(1, result.size());
        assertEquals(Entities.getCandidateJunior(), result.get(0));
        verify(candidateDAO).getAllCandidates();
    }


    @Test
    @DisplayName("create Candidate by Mail")
    void createCandidateByMaile() {
        Candidate candidate = Entities.getCandidateJunior();

        when(candidateDAO.createByMail(candidate.getEmail())).thenReturn(candidate);

        Candidate result = candidateService.createCandidateByMail(candidate.getEmail());

        assertEquals(candidate, result);
        verify(candidateDAO).createByMail(candidate.getEmail());
    }

}

