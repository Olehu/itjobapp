package com.itjobapp.Controller.api;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.domain.Candidate;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/candidates")
public class CandidateApiController {

    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getCandidates(
            @RequestParam(name = "availabilityStatus", required = false) Boolean availabilityStatus,
            @RequestParam(name = "skills", required = false) Set<String> skills
    ) {
        List<CandidateDTO> candidates;
        if (availabilityStatus != null || skills != null) {
            candidates = candidateService.searchCandidates(availabilityStatus, skills).stream()
                    .map(candidateMapper::map)
                    .toList();
        } else {
            candidates = candidateService.getAllCandidates().stream()
                    .map(candidateMapper::map)
                    .toList();
        }
        return ResponseEntity.ok(candidates);
    }

    @PostMapping
    public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        Candidate candidate = candidateMapper.maper(candidateDTO);
        candidateService.createCandidate(candidate);
        return ResponseEntity.ok(candidateDTO);
    }

    @GetMapping("/{candidateEmail}")
    public ResponseEntity<Candidate> getCandidateProfile(@PathVariable String candidateEmail) {
        Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);
        if (candidate != null) {
            return ResponseEntity.ok(candidate);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/hire/{candidateEmail}")
    public ResponseEntity<Void> hireCandidate(@PathVariable String candidateEmail) {
        Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);
        if (candidate != null) {
            candidate = candidate.withAvailabilityStatus(false);
            candidateService.update(candidate);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/image/{candidateEmail}")
    public ResponseEntity<byte[]> getCandidateProfileImage(@PathVariable String candidateEmail) {
        Candidate candidate = candidateService.findCandidateByEmail(candidateEmail);
        if (candidate != null && candidate.getProfileImage() != null) {
            return ResponseEntity.ok(candidate.getProfileImage());
        }
        return ResponseEntity.notFound().build();
    }
}
