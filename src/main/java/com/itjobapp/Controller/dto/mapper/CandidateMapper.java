package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Service.domain.Candidate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateDTO map(final Candidate candidate);

}
