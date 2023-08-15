package com.itjobapp.Controller.web;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.mapper.CandidateMapper;
import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.domain.Candidate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class CandidateController {

    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;

    @GetMapping(value = "/candidate")
    public String candidatePage (Model model){
        var allCandidate = candidateService.getAllCandidates().stream()
                .map(candidateMapper::map)
                .toList();
        model.addAttribute("candidates", allCandidate);
        return "candidate";
    }


    @GetMapping(value ="/candidate/new")
    public String showCandidateForm(Model model) {
        model.addAttribute("candidate", new CandidateDTO());
        return "candidate-form";
    }

    @PostMapping(value ="/candidate/new")
    public String createcandidateDTO(@ModelAttribute("candidate") CandidateDTO candidateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "candidate";
        }

        candidateService.createCandidate(candidateMapper.maper(candidateDTO));
        return "redirect:/candidate";
    }


    @GetMapping(value = "/candidate/profile/{candidateEmail}")
    public String showCandidateProfile(
            @PathVariable String candidateEmail, Model model) {
        Optional<Candidate> candidateOptional = Optional.of(candidateService.getCandidateByEmail(candidateEmail));

        if (candidateOptional.isPresent()) {
            model.addAttribute("candidate", candidateOptional.get());
            return "candidate-profile";
        } else {

            return "candidate-not-found";
        }
    }
}
