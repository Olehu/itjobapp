package com.itjobapp.Database.repository.mapper;


import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobOfferEntityMapper {

    JobOfferEntity mapToEntity(JobOffer JobOffer);

    @Mapping(target = "company.jobOffers", ignore = true)
    JobOffer mapFromEntity(JobOfferEntity jobOfferEntity);

    default JobOfferEntity mapToEntity(JobOffer jobOffer, Company company) {
        return JobOfferEntity.builder()
                .name(jobOffer.getName())
                .experienceLevel(jobOffer.getExperienceLevel())
                .otherRequirements(jobOffer.getOtherRequirements())
                .skills(jobOffer.getSkills())
                .company(CompanyEntity.builder()
                        .companyName(company.getCompanyName())
                        .industry(company.getIndustry())
                        .city(company.getCity())
                        .email(company.getEmail())
                        .isHiring(company.getIsHiring())
                        .build())
                .build();

    }
}
