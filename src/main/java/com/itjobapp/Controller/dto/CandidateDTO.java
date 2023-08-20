package com.itjobapp.Controller.dto;

import com.itjobapp.Database.entity.SkillsEntity;
import jakarta.persistence.Lob;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
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
