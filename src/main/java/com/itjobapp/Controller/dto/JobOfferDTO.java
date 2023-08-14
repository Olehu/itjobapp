package com.itjobapp.Controller.dto;

import com.itjobapp.Service.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDTO {
    String name;
    String experienceLevel;
    String otherRequirements;
    CompanyDTO company;

}
