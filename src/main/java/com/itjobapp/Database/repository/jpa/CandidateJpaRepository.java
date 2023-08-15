package com.itjobapp.Database.repository.jpa;

import com.itjobapp.Database.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Integer>  {
    Optional<CandidateEntity> findByEmail(String email);
}
