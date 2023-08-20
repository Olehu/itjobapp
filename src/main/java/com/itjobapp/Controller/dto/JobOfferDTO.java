package com.itjobapp.Controller.dto;

import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.Skills;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class JobOfferDTO {
    String name;
    String experienceLevel;
    String otherRequirements;
    String remote;
    CompanyDTO company;
    Set<SkillsDTO> skills;

}
