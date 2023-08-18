package com.itjobapp.Service.domain;

import com.itjobapp.Database.entity.CompanyEntity;

import com.itjobapp.Database.entity.SkillsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;
import java.util.Set;

@Value
@Builder
@With
@AllArgsConstructor
public class JobOffer {

    String name;
    String experienceLevel;
    String otherRequirements;
    String remote;
    Set<Skills> skills;
    Company company;

}
