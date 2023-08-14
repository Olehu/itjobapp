package com.itjobapp.Service;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.repository.mapper.CompanyEntityMapper;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.CompanyDAO;
import com.itjobapp.Service.dao.JobOfferDAO;
import com.itjobapp.Service.domain.JobOffer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobOfferService {

    private final JobOfferDAO jobOfferDao;
    private final JobOfferEntityMapper jobOfferEntityMapper;
    public JobOffer createJobOffer(JobOffer jobOffer) {
        return jobOfferDao.create(jobOffer);
    }

    public List<JobOffer> getAllJobOffer() {
        List<JobOfferEntity> jobOfferEntities = jobOfferDao.getAllJobOffer();
        return jobOfferEntities.stream()
                .map(jobOfferEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
