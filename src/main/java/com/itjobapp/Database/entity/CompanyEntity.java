package com.itjobapp.Database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "companyId")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "company_name")
    private String companyName;
    @Column(name = "industry")
    private String industry;
    @Column(name = "location")
    private String location;
    @Column(name = "contact_email")
    private String contactEmail;
    @Column(name = "is_hiring")
    private Boolean isHiring;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<JobOfferEntity> jobOffers;

}
