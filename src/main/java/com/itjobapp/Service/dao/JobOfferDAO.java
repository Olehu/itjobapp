package com.itjobapp.Service.dao;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;

import java.util.List;
import java.util.Optional;

public interface JobOfferDAO {

    JobOffer create(JobOffer jobOffer);

    List<JobOffer> getAllJobOffer();

    Optional<JobOffer> findByJobOfferName(String jobOfferName);

    JobOffer update(JobOffer updated);

    void delete(String name);
}
