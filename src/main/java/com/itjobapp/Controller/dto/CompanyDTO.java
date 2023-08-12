package com.itjobapp.Controller.dto;

import com.itjobapp.Service.domain.JobOffer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    Integer companyId;
    String companyName;
    String industry;
    String location;
    String contactEmail;
    Boolean isHiring;
    Set<JobOfferDTO> jobOffers;
}
