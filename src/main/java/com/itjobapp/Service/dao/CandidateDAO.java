package com.itjobapp.Service.dao;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Service.domain.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface CandidateDAO {
    List<CandidateEntity> getAllCompanies();

    Candidate create(Candidate candidate);

    Optional<Candidate> findByEmail(String candidateName);

    Candidate createByMail(String email);

    Candidate update(Candidate existingCandidate);

    Candidate saveImage(MultipartFile imageFile, Candidate existingCandidate);
}
