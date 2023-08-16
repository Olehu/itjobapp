package com.itjobapp.Database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;

@Getter
@Setter
@EqualsAndHashCode(of = "candidateId")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@With
@Table(name = "candidate")
public class CandidateEntity {
    @Id
    @Column(name = "candidate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer candidateId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "skills")
    private String skills;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "availability_status")
    private Boolean available;
//
//    @Column(name = "profile_image")
//    private byte profileImage;

}
