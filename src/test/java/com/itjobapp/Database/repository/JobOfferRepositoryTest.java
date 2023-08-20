package com.itjobapp.Database.repository;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.repository.JobOfferRepository;
import com.itjobapp.Database.repository.mapper.CompanyEntityMapper;
import com.itjobapp.ItjobappApplication;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Util.Entities;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
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
