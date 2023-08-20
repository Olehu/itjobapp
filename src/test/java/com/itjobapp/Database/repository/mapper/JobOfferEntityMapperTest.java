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

public class JobOfferEntityMapperTest {

    @InjectMocks
    private JobOfferEntityMapper jobOfferEntityMapper = Mappers.getMapper(JobOfferEntityMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMapToEntity() {
        Set<Skills> skills = Entities.setOfSkills();
        Company company = Entities.getCompanyABC();
        JobOffer jobOffer = Entities.jobOfferJunior().withCompany(company).withSkills(skills);

        JobOfferEntity jobOfferEntity = jobOfferEntityMapper.mapToEntity(jobOffer, company);

        assertEquals(jobOffer.getName(), jobOfferEntity.getName());
        assertEquals(jobOffer.getExperienceLevel(), jobOfferEntity.getExperienceLevel());
        assertEquals(jobOffer.getOtherRequirements(), jobOfferEntity.getOtherRequirements());
        assertEquals(jobOffer.getRemote(), jobOfferEntity.getRemote());
        assertEquals(jobOffer.getCompany().getCompanyName(), jobOfferEntity.getCompany().getCompanyName());
    }

    @Test
    public void testMapFromEntity() {
        Set<SkillsEntity> skillsEntities = Entities.setOfSkillsEntity();


        CompanyEntity companyEntity = Entities.getCompanyEntityABC();

        JobOfferEntity jobOfferEntity = Entities.jobOfferentityJunior().withCompany(companyEntity).withSkills(skillsEntities);

        JobOffer jobOffer = jobOfferEntityMapper.mapFromEntity(jobOfferEntity);

        assertEquals(jobOfferEntity.getName(), jobOffer.getName());
        assertEquals(jobOfferEntity.getExperienceLevel(), jobOffer.getExperienceLevel());
        assertEquals(jobOfferEntity.getOtherRequirements(), jobOffer.getOtherRequirements());
        assertEquals(jobOfferEntity.getRemote(), jobOffer.getRemote());
        assertEquals(jobOfferEntity.getSkills().size(), jobOffer.getSkills().size());
        assertEquals(jobOfferEntity.getCompany().getCompanyName(), jobOffer.getCompany().getCompanyName());
    }
}
