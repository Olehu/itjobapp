package com.itjobapp.Controller.dto.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {
    JobOfferMapper map(final JobOfferMapper jobOffer)

}
