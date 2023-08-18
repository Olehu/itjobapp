package com.itjobapp.Database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "companyId")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@With
@Table(name = "company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "industry")
    private String industry;

    @Column(name = "city")
    private String city;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "is_hiring")
    private Boolean isHiring;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<JobOfferEntity> jobOffers;


    public Set<JobOfferEntity> getJobOffers() {
        return Objects.isNull(jobOffers) ? new HashSet<>() : jobOffers;
    }
}
