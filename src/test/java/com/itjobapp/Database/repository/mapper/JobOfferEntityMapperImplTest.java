package com.itjobapp.Database.repository.mapper;

import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.entity.SkillsEntity;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Service.domain.Skills;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class JobOfferEntityMapperImplTest {

    @InjectMocks
    private JobOfferEntityMapperImpl jobOfferEntityMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMapToEntity() {
        Set<Skills> skills = new HashSet<>();
        skills.add(Skills.builder().skillName("Java").build());

        Company company = Company.builder()
                .companyName("TestCompany")
                .industry("IT")
                .city("TestCity")
                .email("test@example.com")
                .isHiring(true)
                .description("TestDescription")
                .build();

        JobOffer jobOffer = JobOffer.builder()
                .name("TestJob")
                .experienceLevel("Senior")
                .otherRequirements("TestRequirements")
                .remote("Remote")
                .skills(skills)
                .company(company)
                .build();

        JobOfferEntity jobOfferEntity = jobOfferEntityMapper.mapToEntity(jobOffer);

        assertNotNull(jobOfferEntity);
        assertEquals(jobOffer.getName(), jobOfferEntity.getName());
        assertEquals(jobOffer.getExperienceLevel(), jobOfferEntity.getExperienceLevel());
        assertEquals(jobOffer.getOtherRequirements(), jobOfferEntity.getOtherRequirements());
        assertEquals(jobOffer.getRemote(), jobOfferEntity.getRemote());
        assertEquals(jobOffer.getSkills().size(), jobOfferEntity.getSkills().size());
        assertEquals(jobOffer.getCompany().getCompanyName(), jobOfferEntity.getCompany().getCompanyName());
    }

    @Test
    public void testSkillsSetToSkillsEntitySet() {
        Set<Skills> skillsSet = new HashSet<>();
        skillsSet.add(Skills.builder().skillName("Java").build());
        skillsSet.add(Skills.builder().skillName("Python").build());

        Set<SkillsEntity> skillsEntitySet = jobOfferEntityMapper.skillsSetToSkillsEntitySet(skillsSet);

        assertNotNull(skillsEntitySet);
        skillsEntitySet.forEach(skillsEntity -> {
            assertTrue(skillsSet.stream().anyMatch(skills -> skills.getSkillName().equals(skillsEntity.getSkillName())));
        });
    }

    @Test
    public void testJobOfferSetToJobOfferEntitySet() {
        Set<JobOffer> jobOfferSet = new HashSet<>();
        jobOfferSet.add(JobOffer.builder().name("TestJob1").experienceLevel("Senior").otherRequirements("TestRequirements1").remote("Remote").build());
        jobOfferSet.add(JobOffer.builder().name("TestJob2").experienceLevel("Junior").otherRequirements("TestRequirements2").remote("Onsite").build());

        Set<JobOfferEntity> jobOfferEntitySet = jobOfferEntityMapper.jobOfferSetToJobOfferEntitySet(jobOfferSet);

        assertNotNull(jobOfferEntitySet);
        jobOfferEntitySet.forEach(jobOfferEntity -> {
            assertTrue(jobOfferSet.stream().anyMatch(jobOffer -> jobOffer.getName().equals(jobOfferEntity.getName())));
        });
    }

    @Test
    public void testCompanyToCompanyEntity() {
        Company company = Company.builder()
                .companyName("TestCompany")
                .industry("IT")
                .city("TestCity")
                .email("test@example.com")
                .isHiring(true)
                .description("TestDescription")
                .build();

        CompanyEntity companyEntity = jobOfferEntityMapper.companyToCompanyEntity(company);

        assertNotNull(companyEntity);
        assertEquals(company.getCompanyName(), companyEntity.getCompanyName());
        assertEquals(company.getIndustry(), companyEntity.getIndustry());
        assertEquals(company.getCity(), companyEntity.getCity());
        assertEquals(company.getEmail(), companyEntity.getEmail());
        assertEquals(company.getIsHiring(), companyEntity.getIsHiring());
        assertEquals(company.getDescription(), companyEntity.getDescription());
    }


}
