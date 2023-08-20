package com.itjobapp.Service;

import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Service.dao.JobOfferDAO;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class JobOfferServiceTest {


    @Mock
    private JobOfferDAO jobOfferDao;

    @Mock
    private JobOfferMapper jobOfferMapper;

    @InjectMocks
    private JobOfferService jobOfferService;

    @Test
    @DisplayName("Should create job offer")
    void createJobOffer() {
        JobOffer jobOffer = Entities.jobOfferJunior();

        when(jobOfferDao.create(jobOffer)).thenReturn(jobOffer);

        JobOffer result = jobOfferService.createJobOffer(jobOffer);

        assertEquals(jobOffer, result);
        verify(jobOfferDao).create(jobOffer);
    }

    @Test
    @DisplayName("Should get all job offers")
    void getAllJobOffer() {
        List<JobOffer> expectedJobOffers = List.of(Entities.jobOfferJunior(), Entities.jobOfferMid());

        when(jobOfferDao.getAllJobOffer()).thenReturn(expectedJobOffers);

        List<JobOffer> result = jobOfferService.getAllJobOffer();

        assertEquals(expectedJobOffers, result);
        verify(jobOfferDao).getAllJobOffer();
    }

    @Test
    @DisplayName("Should get job offer by name")
    void getJobOfferByName() {
        String jobOfferName = "Test Job Offer";
        JobOffer expectedJobOffer = Entities.jobOfferJunior().withName(jobOfferName);

        when(jobOfferDao.findByJobOfferName(jobOfferName)).thenReturn(Optional.of(expectedJobOffer));

        JobOffer result = jobOfferService.getJobOfferByName(jobOfferName);

        assertEquals(expectedJobOffer, result);
        verify(jobOfferDao).findByJobOfferName(jobOfferName);
    }

    @Test
    @DisplayName("Should update job offer")
    void updateJobOffer() {
        JobOffer jobOffer = Entities.jobOfferJunior();


        when(jobOfferDao.update(jobOffer)).thenReturn(jobOffer);

        JobOffer result = jobOfferService.update(jobOffer);

        assertEquals(jobOffer, result);
        verify(jobOfferDao).update(jobOffer);
    }

    @Test
    @DisplayName("Should delete job offer by name")
    void deleteJobOffer() {



        jobOfferService.delete(Entities.jobOfferJunior().getName());

        verify(jobOfferDao).delete(Entities.jobOfferJunior().getName());
    }

    @Test
    @DisplayName("Should search Job Offer")
    void searchJobOffer() {

        List<JobOffer> jobOffers = List.of(Entities.jobOfferJunior(), Entities.jobOfferMid());

        when(jobOfferDao.getAllJobOffer()).thenReturn(jobOffers);

        List<JobOffer> result = jobOfferService.searchJobOffers("Junior", "Remote", Entities.setOfSkillsName());


        assertEquals(Entities.jobOfferJunior(), result.get(0));
        verify(jobOfferDao).getAllJobOffer();
    }
}
