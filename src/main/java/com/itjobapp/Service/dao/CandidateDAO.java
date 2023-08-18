package com.itjobapp.Service.dao;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Service.domain.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface CandidateDAO {
    List<CandidateEntity> getAllCandidates();

    Candidate create(Candidate candidate);

    Optional<Candidate> findByEmail(String candidateName);

    Candidate createByMail(String email);

    Candidate update(Candidate existingCandidate);

    Candidate saveImage(MultipartFile imageFile, Candidate existingCandidate);

    void setProfileImage(String imageName, String email);

    Optional<Candidate> findById(Integer candidateId);
}
