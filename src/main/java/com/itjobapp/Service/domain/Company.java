package com.itjobapp.Service.domain;

import com.itjobapp.Database.entity.JobOfferEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Set;

@Value
@Builder
@With
public class Company {

    Integer companyId;
    String companyName;
    String industry;
    String location;
    String contactEmail;
    Boolean isHiring;
    Set<JobOffer> jobOffers;
}