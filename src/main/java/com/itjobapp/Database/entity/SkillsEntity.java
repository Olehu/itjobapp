package com.itjobapp.Database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "skillId")
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@With
@Table(name = "skills")
public class SkillsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer skillId;

    @Column(name = "skill_name")
    private String skillName;
}
