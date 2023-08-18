package com.itjobapp.Service.domain;

import com.itjobapp.Database.entity.SkillsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Set;

@With
@Value
@Builder
public class Candidate {


    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String experiencelevel;
    Boolean availabilityStatus;
    String description;
    byte[] profileImage;
    Set<Skills> skills;
}
