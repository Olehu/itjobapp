package com.itjobapp.Security;

import com.itjobapp.Database.entity.JobOfferEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_app_role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UserEntity> users;

}

