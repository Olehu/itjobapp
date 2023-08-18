package com.itjobapp.Controller.dto;

import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Service.domain.JobOffer;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    String companyName;
    String industry;
    String city;
    String email;
    Boolean isHiring;
    String description;
    Set<JobOfferDTO> jobOffers;

    public Set<JobOfferDTO> getJobOffers() {
        return Objects.isNull(jobOffers) ? new HashSet<>() : jobOffers;
    }


}
