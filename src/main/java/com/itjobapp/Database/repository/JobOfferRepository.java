package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.repository.jpa.JobOfferJpaRepository;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.JobOfferDAO;
import com.itjobapp.Service.domain.JobOffer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class JobOfferRepository implements JobOfferDAO {

    private final JobOfferEntityMapper jobOfferEntityMapper;
    private final JobOfferJpaRepository jobOfferJpaRepository;

    @Override
    public JobOffer create(JobOffer jobOffer) {

        log.error("START JOB OFFFER CREATION");
        JobOfferEntity toSave = jobOfferEntityMapper.mapToEntity(jobOffer);
        log.error("JOB OFFER: TO SAVE" + toSave);
        JobOfferEntity saved = jobOfferJpaRepository.save(toSave);
        log.error("JOB OFFER: SAVED" + saved);
        return jobOfferEntityMapper.mapFromEntity(saved);
    }

    @Override
    public List<JobOfferEntity> getAllJobOffer() {
        return jobOfferJpaRepository.findAll();
    }
}
