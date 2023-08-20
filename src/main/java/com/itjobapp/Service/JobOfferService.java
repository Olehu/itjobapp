package com.itjobapp.Service;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Controller.web.exception.NotFoundException;
import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.repository.mapper.CompanyEntityMapper;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.CompanyDAO;
import com.itjobapp.Service.dao.JobOfferDAO;
import com.itjobapp.Service.domain.JobOffer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobOfferService {

    private final JobOfferDAO jobOfferDao;
    private final JobOfferMapper jobOfferMapper;
    public JobOffer createJobOffer(JobOffer jobOffer) {
        return jobOfferDao.create(jobOffer);
    }

    public List<JobOffer> getAllJobOffer() {
     return  jobOfferDao.getAllJobOffer();
    }

    public JobOffer getJobOfferByName(String jobOfferName) {
        return jobOfferDao.findByJobOfferName(jobOfferName)
                .orElseThrow(() -> new NotFoundException("JobOffer not found"));
    }

    public JobOffer update(JobOffer updated) {
        return jobOfferDao.update(updated);
    }

    @Transactional
    public void delete(String name) {
        jobOfferDao.delete(name);
    }

    public List<JobOffer> searchJobOffers(String experienceLevel, String remote, Set<String> skills) {

        List<JobOffer> jobOffers = jobOfferDao.getAllJobOffer();
        return jobOffers.stream()
                .filter(jobOfferEntity -> {
                    boolean matches = true;

                    if (experienceLevel != null && !experienceLevel.isEmpty()) {
                        matches = jobOfferEntity.getExperienceLevel().equalsIgnoreCase(experienceLevel);
                    }

                    if (remote != null && !remote.isEmpty()) {
                        matches = jobOfferEntity.getRemote().equalsIgnoreCase(remote);
                    }

                    if (skills != null && !skills.isEmpty()) {
                        Set<String> jobOfferSkills = jobOfferEntity.getSkills().stream()
                                .map(skill -> skill.getSkillName())
                                .collect(Collectors.toSet());

                        matches = skills.stream().anyMatch(skill -> jobOfferSkills.contains(skill));
                    }

                    return matches;
                })
                .collect(Collectors.toList());
    }

}
