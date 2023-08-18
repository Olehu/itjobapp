package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.SkillsDTO;
import com.itjobapp.Service.domain.Skills;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillsMapper {
    SkillsDTO map(final Skills skill);
    Skills maper(final SkillsDTO skilldto);

}
