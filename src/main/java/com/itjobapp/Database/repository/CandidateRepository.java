package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.repository.jpa.JobOfferJpaRepository;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.CandidateDAO;
import com.itjobapp.Service.dao.JobOfferDAO;
import com.itjobapp.Service.domain.JobOffer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CandidateRepository implements CandidateDAO {


}
