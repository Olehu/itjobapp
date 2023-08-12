package com.itjobapp.Database.repository.jpa;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferJpaRepository extends JpaRepository<JobOfferEntity, Integer> {
}
