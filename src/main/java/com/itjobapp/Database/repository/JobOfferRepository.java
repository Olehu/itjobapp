package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.repository.jpa.JobOfferJpaRepository;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.JobOfferDAO;
import com.itjobapp.Service.domain.JobOffer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JobOfferRepository implements JobOfferDAO {

    private final JobOfferEntityMapper jobOfferEntityMapper;
    private final JobOfferJpaRepository jobOfferJpaRepository;

    @Override
    public JobOffer create(JobOffer jobOffer) {

        JobOfferEntity toSave = jobOfferEntityMapper.mapToEntity(jobOffer);
        JobOfferEntity saved = jobOfferJpaRepository.save(toSave);
        return jobOfferEntityMapper.mapFromEntity(saved);
    }

    @Override
    public List<JobOfferEntity> getAllJobOffer() {
        return jobOfferJpaRepository.findAll();
    }
}
