package com.itjobapp.Controller.dto;

import com.itjobapp.Service.domain.JobOffer;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    String companyName;
    String industry;
    String location;
    String email;
    Boolean isHiring;
    Set<JobOfferDTO> jobOffers;
}
