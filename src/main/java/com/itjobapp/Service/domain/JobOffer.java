package com.itjobapp.Service.domain;

import com.itjobapp.Database.entity.CompanyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@Builder
@With
public class JobOffer {

    Integer jobOfferId;
    String name;
    String experienceLevel;
    String otherRequirements;
    Company company;
}
