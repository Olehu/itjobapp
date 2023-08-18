package com.itjobapp.Service;


import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.repository.jpa.CandidateJpaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@AllArgsConstructor
public class ImageService {

    private final CandidateJpaRepository candidateJpaRepository;

    @Transactional
    public void saveImage(MultipartFile file) throws IOException {
        CandidateEntity image = new CandidateEntity().builder()
                .email(file.getOriginalFilename())
                .profileImage(file.getBytes())
                .build();

        candidateJpaRepository.save(image);
    }

    public byte[] getProfileImage(String email) {
        CandidateEntity candidate = candidateJpaRepository.findByEmail(email).get();
        if (candidate != null) {
            return candidate.getProfileImage();
        }
        return null;
    }


}