package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.repository.jpa.CandidateJpaRepository;
import com.itjobapp.Database.repository.mapper.CandidateEntityMapper;
import com.itjobapp.Service.dao.CandidateDAO;
import com.itjobapp.Service.domain.Candidate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CandidateRepository implements CandidateDAO {

    private final CandidateJpaRepository candidateJpaRepository;
    private final CandidateEntityMapper candidateEntityMapper;
    @Override
    public List<CandidateEntity> getAllCompanies() {
        return candidateJpaRepository.findAll();
    }

    @Override
    public Candidate create(Candidate candidate) {
        CandidateEntity toSave = candidateEntityMapper.mapToEntity(candidate);
        CandidateEntity saved = candidateJpaRepository.save(toSave);
        Candidate newCandidate = candidateEntityMapper.mapFromEntity(saved);
        return newCandidate;
    }

    @Override
    public Optional<Candidate> findByEmail(String candidateEmail) {

        Optional<CandidateEntity> candidateEntity = candidateJpaRepository.findByEmail(candidateEmail);
        return candidateEntity.map(candidateEntityMapper::mapFromEntity);
    }

    @Override
    public Candidate createByMail(String email) {
        CandidateEntity tosave = new CandidateEntity().builder().email(email).build();
        CandidateEntity saved = candidateJpaRepository.save(tosave);
        return candidateEntityMapper.mapFromEntity(saved);

    }

    @Override
    public Candidate update(Candidate existingCandidate) {
        CandidateEntity search = candidateJpaRepository.findByEmail(existingCandidate.getEmail()).orElseThrow(()
                -> new RuntimeException("Candidate not found"));

        CandidateEntity toSave =
                search.withEmail(existingCandidate.getEmail())
                        .withFirstName(existingCandidate.getFirstName())
                        .withLastName(existingCandidate.getLastName())
                        .withPhoneNumber(existingCandidate.getPhoneNumber())
                        .withSkills(existingCandidate.getSkills())
                        .withAvailable(existingCandidate.getAvailable())
                        .withProfileImage(existingCandidate.getProfileImage());
        CandidateEntity saved = candidateJpaRepository.save(toSave);
        return candidateEntityMapper.mapFromEntity(saved);

    }

    @Override
    public Candidate saveImage(MultipartFile imageFile, Candidate existingCandidate) {
        CandidateEntity search = candidateJpaRepository.findByEmail(existingCandidate.getEmail()).orElseThrow(()
                -> new RuntimeException("Candidate not found"));

        CandidateEntity saved = null;
        try {
            byte[] image = imageFile.getBytes();
            saved = candidateJpaRepository.save(search.withProfileImage(image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return candidateEntityMapper.mapFromEntity(saved);

    }
}
