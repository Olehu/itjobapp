package com.itjobapp.Service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public enum SkillList {
    JAVA("Java"),
    PYTHON("Python"),
    C_SHARP("C#"),
    HTML("HTML"),
    CSS("CSS"),
    JAVASCRIPT("JavaScript"),
    SQL("SQL"),
    SPRING("Spring Framework"),
    REACT("React"),
    ANGULAR("Angular"),
    NODEJS("Node.js"),
    DJANGO("Django"),
    RUBY("Ruby"),
    PHP("PHP"),
    AWS("Amazon Web Services"),
    DOCKER("Docker"),
    GIT("Git"),
    C_PLUS_PLUS("C++");

    private final String displayName;

    SkillList(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<SkillList> getAllSkills() {
        return Arrays.asList(values());
    }
}
