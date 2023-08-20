package com.itjobapp.Controller.web;

import com.itjobapp.Service.SkillList;
import com.itjobapp.Service.domain.Skills;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceController {


    public static List<String> getAllSkillsAsString() {
        List<String> set = SkillList.getAllSkillsAsString();
        return set;
    }

    public static Set<Skills> getAllSkillsAsSkillSet() {
        Set<Skills> list = SkillList.getAllSkillsAsString().stream()
                .map(a -> Skills.builder().skillName(a).build())
                .collect(Collectors.toSet());
        return list;
    }
}
