package com.itjobapp.Service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class Candidate {


    Integer candidateId;
    String firstName;
    String lastName;
    String email;
    String skills;
    String phoneNumber;
    Boolean available;
    String profileImage;
}
