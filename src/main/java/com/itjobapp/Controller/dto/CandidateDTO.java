package com.itjobapp.Controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {

    String firstName;
    String lastName;
    String email;
    String skills;
    String phoneNumber;
    Boolean availabilityStatus;


}
