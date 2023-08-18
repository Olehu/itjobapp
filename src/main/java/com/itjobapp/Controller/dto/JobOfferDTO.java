package com.itjobapp.Controller.dto;

import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.Skills;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDTO {
    String name;
    String experienceLevel;
    String otherRequirements;
    String remote;
    CompanyDTO company;
    Set<SkillsDTO> skills;

}
