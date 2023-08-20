package com.itjobapp.Database.repository.jpa;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.SkillsEntity;
import com.itjobapp.Service.domain.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SkillsJpaRepository extends JpaRepository<SkillsEntity, Integer>  {
    Optional<SkillsEntity> searchBySkillName(String skillName)    ;
}
