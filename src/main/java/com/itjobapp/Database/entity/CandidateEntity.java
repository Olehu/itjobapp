package com.itjobapp.Database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Set;

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

    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "availability_status")
    private Boolean availabilityStatus;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "profile_image", columnDefinition = "bytea")
    private byte[] profileImage;


    @ManyToMany
    @JoinTable(
            name = "candidate_skills",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<SkillsEntity> skills;
}
