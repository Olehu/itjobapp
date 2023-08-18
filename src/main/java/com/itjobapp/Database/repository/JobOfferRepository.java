package com.itjobapp.Database.repository;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.repository.jpa.CompanyJpaRepository;
import com.itjobapp.Database.repository.jpa.JobOfferJpaRepository;
import com.itjobapp.Database.repository.mapper.CompanyEntityMapper;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.JobOfferDAO;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class JobOfferRepository implements JobOfferDAO {

    private final JobOfferEntityMapper jobOfferEntityMapper;
    private final JobOfferJpaRepository jobOfferJpaRepository;
    private final CompanyJpaRepository  companyJpaRepository;
    private final CompanyEntityMapper companyEntityMapper;

    @Override
    public JobOffer create(JobOffer jobOffer) {

        log.error("CREATE:" + jobOffer);


        CompanyEntity companyEntity = companyJpaRepository.findByCompanyName(jobOffer.getCompany().getCompanyName()).get();
        Company company = companyEntityMapper.mapFromEntity(companyEntity);
        //        CompanyEntity companyEntity = jobOfferEntityMapper.mapToEntity(jobOffer).getCompany();
        log.error("COMPANY:" + companyEntity);

        JobOfferEntity toSave = jobOfferEntityMapper.mapToEntity(jobOffer, company);
        log.error("TO SAVE:" + toSave);

        JobOfferEntity saved = jobOfferJpaRepository.save(toSave.withCompany(companyEntity));
        log.error("SAVE:" + saved);
        return jobOfferEntityMapper.mapFromEntity(saved);
    }

    @Override
    public List<JobOfferEntity> getAllJobOffer() {
        return jobOfferJpaRepository.findAll();
    }

    @Override
    public Optional<JobOffer> findByJobOfferName(String jobOfferName) {

        Optional<JobOfferEntity> jobOfferEntity = jobOfferJpaRepository.findByName(jobOfferName);
        return jobOfferEntity.map(jobOfferEntityMapper::mapFromEntity);
    }

    @Override
    public JobOffer update(JobOfferDTO updated) {

        JobOfferEntity jobOfferEntity = jobOfferJpaRepository.findByName(updated.getName()).get();
        jobOfferEntity = jobOfferEntity
                .withSkills(updated.getSkills())
                .withExperienceLevel(updated.getExperienceLevel())
                .withOtherRequirements(updated.getOtherRequirements());

        JobOfferEntity saved = jobOfferJpaRepository.save(jobOfferEntity);
        return jobOfferEntityMapper.mapFromEntity(saved);
    }

    @Override
    public void delete(String name) {
        jobOfferJpaRepository.deleteByName(name);
    }
}
