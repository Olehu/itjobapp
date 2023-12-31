package com.itjobapp.Service;

import com.itjobapp.Controller.web.exception.NotFoundException;
import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.repository.mapper.CandidateEntityMapper;
import com.itjobapp.Service.dao.CandidateDAO;
import com.itjobapp.Service.domain.Candidate;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidateService {

    private final CandidateDAO candidateDao;
    public List<Candidate> getAllCandidates() {
        return candidateDao.getAllCandidates();
    }

    public Candidate createCandidate(Candidate candidate) {
        return candidateDao.create(candidate);
    }

    public Candidate findCandidateByEmail(String candidateEmail) {
        return candidateDao.findByEmail(candidateEmail)
                .orElseThrow(() -> new NotFoundException("Candidate not found"));    }

    public Candidate createCandidateByMail(String email) {
        return candidateDao.createByMail(email);
    }

    public Candidate update(Candidate existingCandidate) {
        return candidateDao.update(existingCandidate);
    }

    @Transactional
    public Candidate saveImage(MultipartFile imageFile, Candidate existingCandidate) {
        return candidateDao.saveImage(imageFile, existingCandidate);
    }


    public List<Candidate> searchCandidates(Boolean available, Set<String> skills) {

        List<Candidate> candidates = candidateDao.getAllCandidates();

        List<Candidate> collect = candidates.stream()
                .filter(candidateEntity -> {
                    boolean matches = true;

                    if (skills != null && !skills.isEmpty() && candidateEntity.getSkills() != null) {
                        Set<String> jobOfferSkills = candidateEntity.getSkills().stream()
                                .map(skill -> skill.getSkillName())
                                .collect(Collectors.toSet());

                        matches = skills.stream().anyMatch(skill -> jobOfferSkills.contains(skill));
                    }

                    if (available != null){
                        matches = matches && candidateEntity.getAvailabilityStatus().equals(available);
                    }

                    return matches;
                })
                .collect(Collectors.toList());

        return collect;
    }



}
