package com.itjobapp.Controller.dto;

import jakarta.validation.constraints.Email;
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
    @Email
    String email;
    String phoneNumber;
    String experiencelevel;
    Boolean availabilityStatus;
    String description;
    byte[] profileImage;
    Set<SkillsDTO> skills;




}
