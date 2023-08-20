package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.SkillsEntity;
import com.itjobapp.Database.repository.jpa.CandidateJpaRepository;
import com.itjobapp.Database.repository.jpa.SkillsJpaRepository;
import com.itjobapp.Database.repository.mapper.CandidateEntityMapper;
import com.itjobapp.Service.dao.CandidateDAO;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Skills;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.coyote.http11.Constants.a;

@Repository
@AllArgsConstructor
public class CandidateRepository implements CandidateDAO {

    private final CandidateJpaRepository candidateJpaRepository;
    private final CandidateEntityMapper candidateEntityMapper;
    private final SkillsJpaRepository skillsJpaRepository;
    @Override
    public List<Candidate> getAllCandidates() {
        return candidateJpaRepository.findAll().stream().map(candidate -> candidateEntityMapper.mapFromEntity(candidate)).collect(Collectors.toList());
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
        CandidateEntity tosave = new CandidateEntity().builder()
                .email(email)
                .availabilityStatus(false)
                .build();
        CandidateEntity saved = candidateJpaRepository.save(tosave);
        return candidateEntityMapper.mapFromEntity(saved);

    }

    @Override
    public Candidate update(Candidate existingCandidate) {
        CandidateEntity search = candidateJpaRepository.findByEmail(existingCandidate.getEmail()).orElseThrow(()
                -> new RuntimeException("Candidate not found"));

        Set<Skills> skills = existingCandidate.getSkills();
        skills.stream()
                .forEach(a -> {
                    if (skillsJpaRepository.searchBySkillName(a.getSkillName()).isEmpty()) {
                        skillsJpaRepository.save(SkillsEntity.builder().skillName(a.getSkillName()).build());
                    }
                });

        Set<SkillsEntity> skillsEntityStream = skills.stream()
                .map(skill -> skillsJpaRepository.searchBySkillName(skill.getSkillName()).get())
                .collect(Collectors.toSet());


        CandidateEntity toSave =
                search.withEmail(existingCandidate.getEmail())
                        .withFirstName(existingCandidate.getFirstName())
                        .withLastName(existingCandidate.getLastName())
                        .withPhoneNumber(existingCandidate.getPhoneNumber())
                        .withSkills(skillsEntityStream)
                        .withAvailabilityStatus(existingCandidate.getAvailabilityStatus());
        CandidateEntity saved = candidateJpaRepository.save(toSave);
        return candidateEntityMapper.mapFromEntity(saved);

    }

    @Override
    public Candidate saveImage(MultipartFile imageFile, Candidate existingCandidate) {
        CandidateEntity search = candidateJpaRepository.findByEmail(existingCandidate.getEmail()).orElseThrow(()
                -> new RuntimeException("Candidate not found"));

        try {

            CandidateEntity save = candidateJpaRepository.save(search.withProfileImage(imageFile.getBytes()));
            return candidateEntityMapper.mapFromEntity(save);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setProfileImage(String imageName, String email) {
        CandidateEntity search = candidateJpaRepository.findByEmail(email).orElseThrow(()
                -> new RuntimeException("Candidate not found"));
//        candidateJpaRepository.save(search.withProfileImage(imageName));
    }

    @Override
    public Optional<Candidate> findById(Integer candidateId) {
        CandidateEntity search = candidateJpaRepository.findById(candidateId).orElseThrow(()
                -> new RuntimeException("Candidate not found"));
        return Optional.of(candidateEntityMapper.mapFromEntity(search));
    }


}
