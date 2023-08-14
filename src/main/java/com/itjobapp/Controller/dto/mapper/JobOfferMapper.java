package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Service.domain.JobOffer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {
    JobOfferDTO map(final JobOffer jobOffer);

    JobOffer maper(JobOfferDTO jobOfferDTO);
}
