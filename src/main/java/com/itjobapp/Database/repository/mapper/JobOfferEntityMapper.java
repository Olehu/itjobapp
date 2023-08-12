package com.itjobapp.Database.repository.mapper;


import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.JobOffer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobOfferEntityMapper {

    JobOfferEntity mapToEntity(JobOffer JobOffer);

}
