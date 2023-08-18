package com.itjobapp.Controller.dto;

import com.itjobapp.Database.entity.SkillsEntity;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {

    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String experiencelevel;
    Boolean availabilityStatus;
    String description;
    byte[] profileImage;
    Set<SkillsDTO> skills;




}
