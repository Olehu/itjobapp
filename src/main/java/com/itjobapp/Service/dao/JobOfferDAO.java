package com.itjobapp.Service.dao;

import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;

import java.util.List;

public interface JobOfferDAO {

    JobOffer create(JobOffer jobOffer);

    List<JobOfferEntity> getAllJobOffer();

}
