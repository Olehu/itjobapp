package com.itjobapp.Database.repository.mapper;


import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.entity.SkillsEntity;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Service.domain.Skills;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobOfferEntityMapper {

    JobOfferEntity mapToEntity(JobOffer JobOffer);

    @Mapping(target = "company.jobOffers", ignore = true)
    JobOffer mapFromEntity(JobOfferEntity jobOfferEntity);

    default JobOfferEntity mapToEntity(JobOffer jobOffer, Company company) {
        Set<SkillsEntity> skills = jobOffer.getSkills().stream().map(this::mapSkillsToSkillsEntity).collect(Collectors.toSet());

        return JobOfferEntity.builder()
                .name(jobOffer.getName())
                .experienceLevel(jobOffer.getExperienceLevel())
                .otherRequirements(jobOffer.getOtherRequirements())
                .skills(skills)
                .remote(jobOffer.getRemote())
                .company(CompanyEntity.builder()
                        .companyName(company.getCompanyName())
                        .industry(company.getIndustry())
                        .city(company.getCity())
                        .email(company.getEmail())
                        .isHiring(company.getIsHiring())
                        .build())
                .build();

    }

    default SkillsEntity mapSkillsToSkillsEntity(Skills skills) {
        return SkillsEntity.builder()
                .skillName(skills.getSkillName())
                .build();
    }
}
