package com.itjobapp.Service;

import com.itjobapp.Controller.web.exception.NotFoundException;
import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.repository.mapper.CandidateEntityMapper;
import com.itjobapp.Service.dao.CandidateDAO;
import com.itjobapp.Service.domain.Candidate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidateService {

    private final CandidateDAO candidateDao;
    private final CandidateEntityMapper candidateEntityMapper;
    public List<Candidate> getAllCandidates() {
        List<CandidateEntity> candidateEntities = candidateDao.getAllCompanies();
        return candidateEntities.stream()
                .map(candidateEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    public Candidate createCandidate(Candidate candidate) {
        return candidateDao.create(candidate);
    }

    public Candidate getCandidateByEmail(String candidateEmail) {
        return candidateDao.findByEmail(candidateEmail)
                .orElseThrow(() -> new NotFoundException("Candidate not found"));    }
}
