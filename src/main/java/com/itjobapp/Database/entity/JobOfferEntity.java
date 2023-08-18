package com.itjobapp.Database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "jobOfferId")
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@With
@Table(name = "job_offer")
public class JobOfferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_offer_id")
    private Integer jobOfferId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "experience_level")
    private String experienceLevel;

    @Column(name = "other_requirements", length = 1000)
    private String otherRequirements;


    @Column(name = "remote", length = 20)
    private String remote;

    @ManyToMany
    @JoinTable(
            name = "job_offer_skills",
            joinColumns = @JoinColumn(name = "job_offer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<SkillsEntity> skills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

}
