package com.itjobapp.Database.repository.mapper;

import com.itjobapp.Controller.dto.SkillsDTO;
import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.SkillsEntity;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Skills;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillsEntityMapper {
    SkillsEntity mapToEntity(Skills skills);


    Skills mapFromEntity(SkillsEntity skillsEntity);

}
