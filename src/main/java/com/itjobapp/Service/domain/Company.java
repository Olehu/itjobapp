package com.itjobapp.Service.domain;

import lombok.*;

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
}