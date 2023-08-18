package com.itjobapp.Database.repository.jpa;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobOfferJpaRepository extends JpaRepository<JobOfferEntity, Integer> {
    Optional<JobOfferEntity> findByName(String jobOfferName);

    void deleteByName(String name);
}
