package com.itjobapp.Controller.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class CompanyDTO {
    String companyName;
    String industry;
    String city;
    @Email
    String email;
    Boolean isHiring;
    String description;
    Set<JobOfferDTO> jobOffers;

    public Set<JobOfferDTO> getJobOffers() {
        return Objects.isNull(jobOffers) ? new HashSet<>() : jobOffers;
    }


}
