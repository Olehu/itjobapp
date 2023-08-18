package com.itjobapp.Controller.web;

import com.itjobapp.Service.SkillList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceController {


    public static List<SkillList> getAllSkills() {
        return SkillList.getAllSkills();
    }
}
