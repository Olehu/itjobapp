package com.itjobapp.Database.repository.jpa;

import com.itjobapp.Database.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Integer>  {
    Optional<CandidateEntity> findByEmail(String email);
}
