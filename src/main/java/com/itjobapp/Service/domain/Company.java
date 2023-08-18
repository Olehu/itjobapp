package com.itjobapp.Service.domain;

import com.itjobapp.Database.entity.JobOfferEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {

    String companyName;
    String industry;
    String location;
    String email;
    Boolean isHiring;
    Set<JobOffer> jobOffers;

    public Set<JobOffer> getJobOffers() {
        return Objects.isNull(jobOffers) ? new HashSet<>() : jobOffers;
    }

}