package com.itjobapp.Service.dao;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Service.domain.Candidate;

import java.util.List;
import java.util.Optional;


public interface CandidateDAO {
    List<CandidateEntity> getAllCompanies();

    Candidate create(Candidate candidate);

    Optional<Candidate> findByEmail(String candidateName);
}
