package com.itjobapp.Database.repository.mapper;


import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Service.domain.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEntityMapper {

    CandidateEntity mapToEntity(Candidate candidate);


    Candidate mapFromEntity(CandidateEntity candidateEntity);
}
