package com.itjobapp.Service.domain;

import com.itjobapp.Database.entity.CompanyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@Value
@Builder
@With
@AllArgsConstructor
public class JobOffer {

    String name;
    String experienceLevel;
    String otherRequirements;
    Company company;
}
