package com.itjobapp.Database.repository.jpa;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface JobOfferJpaRepository extends JpaRepository<JobOfferEntity, Integer> {
    Optional<JobOfferEntity> findByName(String jobOfferName);

    void deleteByName(String name);
}
