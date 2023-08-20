package com.itjobapp.Database.repository.mapper;

import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.entity.SkillsEntity;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Service.domain.Skills;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyEntityMapperTest {

    @InjectMocks
    private CompanyEntityMapper companyEntityMapper = Mappers.getMapper(CompanyEntityMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMapToEntity() {
        Company company = Entities.getCompanyABC();

        CompanyEntity companyEntity = companyEntityMapper.mapToEntity(company);

        assertEquals(company.getCompanyName(), companyEntity.getCompanyName());
        assertEquals(company.getIndustry(), companyEntity.getIndustry());
        assertEquals(company.getCity(), companyEntity.getCity());
        assertEquals(company.getEmail(), companyEntity.getEmail());
        assertEquals(company.getIsHiring(), companyEntity.getIsHiring());
        assertEquals(company.getDescription(), companyEntity.getDescription());
        assertEquals(company.getJobOffers(), companyEntity.getJobOffers());
    }

    @Test
    public void testMapFromEntity() {
        JobOfferEntity jobOfferEntity = Entities.jobOfferentityJunior();
        Set<JobOfferEntity> jobOfferEntities = Set.of(jobOfferEntity);

        CompanyEntity companyEntity = Entities.getCompanyEntityABC().withJobOffers(jobOfferEntities);

        Company company = companyEntityMapper.mapFromEntity(companyEntity);

        assertEquals(companyEntity.getCompanyName(), company.getCompanyName());
        assertEquals(companyEntity.getIndustry(), company.getIndustry());
        assertEquals(companyEntity.getCity(), company.getCity());
        assertEquals(companyEntity.getEmail(), company.getEmail());
        assertEquals(companyEntity.getIsHiring(), company.getIsHiring());
        assertEquals(companyEntity.getDescription(), company.getDescription());
        assertEquals(companyEntity.getJobOffers().size(), company.getJobOffers().size());
    }
}
