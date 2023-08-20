package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Service.domain.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateDTO map(final Candidate candidate);

    Candidate maper(CandidateDTO candidateDTO);
}
