package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
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
public class JobOfferRepositoryTest {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private CompanyRepository CompanyRepository;


    @Test
    @Transactional
    public void testCreateCompanyandJobOferandFindByName() {
        JobOffer jobOffer = Entities.jobOfferMid();
        JobOfferEntity jobOfferEntity = Entities.jobOfferentityMid();
        Company company = Entities.getCompanyABC();

        Company  companyCreated = CompanyRepository.create(company);

        JobOffer jobOfferCreated = jobOfferRepository.create(jobOffer);
        JobOffer found = jobOfferRepository.findByJobOfferName(jobOfferEntity.getName()).get();


        assertEquals(jobOffer, jobOfferCreated);
        assertEquals(company, companyCreated);
        assertEquals(jobOffer, found);
    }

    @Test
    @Transactional
    public void testGetAllUpdateAndDeleteJobOffer() {
        JobOffer jobOffer = Entities.jobOfferMid();
        JobOffer jobOffer2 = Entities.jobOfferJunior();

        Company company = Entities.getCompanyABC();
       CompanyRepository.create(company);

        jobOfferRepository.create(jobOffer);
        jobOfferRepository.create(jobOffer2);

        List<JobOffer> allJobOffer = jobOfferRepository.getAllJobOffer();

        jobOfferRepository.delete(jobOffer.getName());
        JobOffer updated = jobOfferRepository.update(jobOffer2.withOtherRequirements("some new Requirements"));

        List<JobOffer> jobOfferAfterDelete = jobOfferRepository.getAllJobOffer();

        assertEquals(2, allJobOffer.size());
        assertEquals(1, jobOfferAfterDelete.size());
        assertEquals(updated, jobOfferAfterDelete.get(0));
        assertNotEquals(jobOffer2, updated);
    }

}
