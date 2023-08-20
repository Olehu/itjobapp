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
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyEntityMapper {

    CompanyEntity mapToEntity(Company company);

    @Mapping(source = "jobOffers", target = "jobOffers", qualifiedByName = "mapJobOffers")
    Company mapFromEntity(CompanyEntity companyEntity);


    @Named("mapJobOffers")
    default Set<JobOffer> mapJobOfferEntities(Set<JobOfferEntity> entities) {
        return entities.stream().map(this::mapJobOfferEntityToJobOffer).collect(Collectors.toSet());
    }


    default JobOffer mapJobOfferEntityToJobOffer(JobOfferEntity jobOfferEntity) {
        Set<Skills> skills = jobOfferEntity.getSkills().stream().map(this::mapSkillsEntityToSkills).collect(Collectors.toSet());
        return JobOffer.builder()
                .name(jobOfferEntity.getName())
                .experienceLevel(jobOfferEntity.getExperienceLevel())
                .otherRequirements(jobOfferEntity.getOtherRequirements())
                .skills(skills)
                .build();
    }

    default Skills mapSkillsEntityToSkills(SkillsEntity skillsEntity) {
        return Skills.builder()
                .skillName(skillsEntity.getSkillName())
                .build();
    }


}
