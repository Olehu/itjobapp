package com.itjobapp.Util;

import com.itjobapp.Controller.dto.CandidateDTO;
import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Controller.dto.SkillsDTO;
import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.entity.SkillsEntity;
import com.itjobapp.Database.repository.mapper.SkillsEntityMapper;
import com.itjobapp.Service.SkillList;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Service.domain.Skills;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Entities {



    public static Company getCompanyABC() {
        return Company.builder()
                .companyName("ABC Company")
                .industry("Technology")
                .city("New York")
                .email("abc@abc.com")
                .isHiring(true)
                .description("asfdjasoidfsa").build();
    }

    public static CompanyDTO getCompanyDTOABC() {
        return CompanyDTO.builder()
                .companyName("ABC Company")
                .industry("Technology")
                .city("New York")
                .email("abc@abc.com")
                .isHiring(true)
                .description("asfdjasoidfsa").build();
    }
    public static Company getCompanyXYZ() {
        return  Company.builder()
                .companyName("XYZ Company")
                .industry("IT")
                .city("London")
                .email("xyz@abc.com")
                .isHiring(false)
                .description("no").build();
    }

    public static JobOffer jobOfferJunior(){
        return  JobOffer.builder()
                .name("Software Engineer")
                .otherRequirements("no")
                .experienceLevel("Junior")
                .remote("Remote")
                .skills(setOfSkills())
                .company(getCompanyABC())
                .build();
    }
    public static JobOffer jobOfferMid(){
        return  JobOffer.builder()
                .name("IT")
                .otherRequirements("FluentEnglis")
                .experienceLevel("Mid")
                .remote("Hybrid")
                .skills(setOfSkills())
                .company(getCompanyABC())
                .build();
    }

    public static JobOfferEntity jobOfferentityJunior(){
        return  JobOfferEntity.builder()
                .name("Software Engineer")
                .otherRequirements("no")
                .experienceLevel("Junior")
                .remote("Remote")
                .skills(setOfSkillsEntity())
                .company(getCompanyEntityABC())
                .build();
    }
    public static JobOfferEntity jobOfferentityMid(){
        return  JobOfferEntity.builder()
                .name("IT")
                .otherRequirements("FluentEnglis")
                .experienceLevel("Mid")
                .remote("Hybrid")
                .skills(setOfSkillsEntity())
                .company(getCompanyEntityABC())
                .build();
    }

    public static Set<Skills> setOfSkills() {
        Skills skill = Skills.builder()
                .skillName("Java")
                .build();
        Skills skillTwo = Skills.builder()
                .skillName("Python")
                .build();

        Set<Skills> skillsSet = Set.of(skill, skillTwo);
        return skillsSet;
    }

    public static Set<SkillsDTO> setOfSkillsDTO() {
        SkillsDTO skill = SkillsDTO.builder()
                .skillName("Java")
                .build();
        SkillsDTO skillTwo = SkillsDTO.builder()
                .skillName("Python")
                .build();

        Set<SkillsDTO> skillsSet = Set.of(skill, skillTwo);
        return skillsSet;
    }

    public static Set<SkillsEntity> setOfSkillsEntity() {
        SkillsEntity skill = SkillsEntity.builder()
                .skillId(1)
                .skillName("Java")
                .build();
        SkillsEntity skillTwo = SkillsEntity.builder()
                .skillId(2)
                .skillName("Python")
                .build();

        Set<SkillsEntity> skillsSet = Set.of(skill, skillTwo);
        return skillsSet;
    }

    public static Set<String> setOfSkillsName() {
        Set<String> skillsSet = Set.of("Java");
        return skillsSet;
    }

    public static Candidate getCandidateJunior() {
        return Candidate.builder()
                .email("junior@junior.pl")
                .availabilityStatus(true)
                .firstName("Jan")
                .lastName("Juniorowski")
                .description("Be Junior")
                .skills(setOfSkills())
                .experiencelevel("Junior")
                .build();
    }

    public static Candidate getCandidateMid() {
        return Candidate.builder()
                .email("mid@mid.pl")
                .firstName("Mike")
                .availabilityStatus(false)
                .lastName("Midowski")
                .description("Be Mid")
                .skills(setOfSkills())
                .experiencelevel("Mid")
                .build();
    }

    public static CandidateDTO getCandidateDTOJunior() {
        return CandidateDTO.builder()
                .email("junior@junior.pl")
                .availabilityStatus(true)
                .firstName("Jan")
                .lastName("Juniorowski")
                .description("Be Junior")
                .skills(setOfSkillsDTO())
                .experiencelevel("Junior")
                .build();
    }

    public static CandidateEntity getCandidateEntityJunior() {
        return CandidateEntity.builder()
                .email("junior@junior.pl")
                .availabilityStatus(true)
                .firstName("Jan")
                .lastName("Juniorowski")
                .description("Be Junior")
                .skills(setOfSkillsEntity())
                .experiencelevel("Junior")
                .build();
    }

    public static CandidateEntity getCandidateEntityMid() {
        return CandidateEntity.builder()
                .email("mid@mid.pl")
                .firstName("Mike")
                .availabilityStatus(false)
                .lastName("Midowski")
                .description("Be Mid")
                .skills(setOfSkillsEntity())
                .experiencelevel("Mid")
                .build();
    }

    public static CompanyEntity getCompanyEntityABC() {
        return CompanyEntity.builder()
                .companyName("ABC Company")
                .industry("Technology")
                .city("New York")
                .email("abc@abc.com")
                .isHiring(true)
                .description("asfdjasoidfsa").build();
    }

    public static CompanyEntity getCompanyEntityXYZ() {
        return  CompanyEntity.builder()
                .companyName("XYZ Company")
                .industry("IT")
                .city("London")
                .email("xyz@abc.com")
                .isHiring(false)
                .description("no").build();
    }
}
