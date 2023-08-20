package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.entity.SkillsEntity;
import com.itjobapp.Database.repository.jpa.CompanyJpaRepository;
import com.itjobapp.Database.repository.jpa.JobOfferJpaRepository;
import com.itjobapp.Database.repository.jpa.SkillsJpaRepository;
import com.itjobapp.Database.repository.mapper.CompanyEntityMapper;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.JobOfferDAO;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Service.domain.Skills;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
@Slf4j
public class JobOfferRepository implements JobOfferDAO {

    private final JobOfferEntityMapper jobOfferEntityMapper;
    private final JobOfferJpaRepository jobOfferJpaRepository;
    private final CompanyJpaRepository  companyJpaRepository;
    private final CompanyEntityMapper companyEntityMapper;
    private final SkillsJpaRepository skillsJpaRepository;

    @Override
    public JobOffer create(JobOffer jobOffer) {

        Set<SkillsEntity> skillsEntityStream = getSkillsEntities(jobOffer);

        CompanyEntity companyEntity = companyJpaRepository.findByEmail(jobOffer.getCompany().getEmail()).get();
        Company company = companyEntityMapper.mapFromEntity(companyEntity);
        JobOfferEntity toSave = jobOfferEntityMapper.mapToEntity(jobOffer, company).withCompany(companyEntity).withSkills(skillsEntityStream);
        JobOfferEntity saved = jobOfferJpaRepository.save(toSave);
        return jobOfferEntityMapper.mapFromEntity(saved);
    }

    @Override
    public List<JobOffer> getAllJobOffer() {
        return jobOfferJpaRepository.findAll().stream().map(jobOffer -> jobOfferEntityMapper.mapFromEntity(jobOffer)).collect(Collectors.toList());
    }

    @Override
    public Optional<JobOffer> findByJobOfferName(String jobOfferName) {
        Optional<JobOfferEntity> jobOfferEntity = jobOfferJpaRepository.findByName(jobOfferName);
        return jobOfferEntity.map(jobOfferEntityMapper::mapFromEntity);
    }

    @Override
    public JobOffer update(JobOffer updated) {
        Set<SkillsEntity> skillsEntityStream = getSkillsEntities(updated);
        JobOfferEntity jobOfferEntity = jobOfferJpaRepository.findByName(updated.getName()).get();
        jobOfferEntity = jobOfferEntity
                .withSkills(skillsEntityStream)
                .withExperienceLevel(updated.getExperienceLevel())
                .withOtherRequirements(updated.getOtherRequirements())
                .withName(updated.getName())
                .withRemote(updated.getRemote());
        JobOfferEntity saved = jobOfferJpaRepository.save(jobOfferEntity);
        return jobOfferEntityMapper.mapFromEntity(saved);
    }

    @Override
    public void delete(String name) {
        jobOfferJpaRepository.deleteByName(name);
    }


    private Set<SkillsEntity> getSkillsEntities(JobOffer jobOffer) {
        Set<Skills> skills = jobOffer.getSkills();
        skills.stream()
                .forEach(a -> {
                    if (skillsJpaRepository.searchBySkillName(a.getSkillName()).isEmpty()) {
                        skillsJpaRepository.save(SkillsEntity.builder().skillName(a.getSkillName()).build());
                    }
                });

        Set<SkillsEntity> skillsEntityStream = skills.stream()
                .map(skill -> skillsJpaRepository.searchBySkillName(skill.getSkillName()).get())
                .collect(Collectors.toSet());
        return skillsEntityStream;
    }
}
