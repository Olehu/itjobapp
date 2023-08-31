package com.itjobapp.Controller.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.itjobapp.Service.SkillsServiceController;
import org.junit.jupiter.api.Test;

class SkillsServiceControllerTest {
    /**
     * Method under test: {@link SkillsServiceController#getAllSkillsAsString()}
     */
    @Test
    void testGetAllSkillsAsString() {
        List<String> actualAllSkillsAsString = SkillsServiceController.getAllSkillsAsString();
        assertEquals(18, actualAllSkillsAsString.size());
        assertEquals("Java", actualAllSkillsAsString.get(0));
        assertEquals("Python", actualAllSkillsAsString.get(1));
        assertEquals("C#", actualAllSkillsAsString.get(2));
        assertEquals("HTML", actualAllSkillsAsString.get(3));
        assertEquals("CSS", actualAllSkillsAsString.get(4));
        assertEquals("JavaScript", actualAllSkillsAsString.get(5));
        assertEquals("Ruby", actualAllSkillsAsString.get(12));
        assertEquals("PHP", actualAllSkillsAsString.get(13));
        assertEquals("Amazon Web Services", actualAllSkillsAsString.get(14));
        assertEquals("Docker", actualAllSkillsAsString.get(15));
        assertEquals("Git", actualAllSkillsAsString.get(Short.SIZE));
        assertEquals("C++", actualAllSkillsAsString.get(17));
    }

    /**
     * Method under test: {@link SkillsServiceController#getAllSkillsAsSkillSet()}
     */
    @Test
    void testGetAllSkillsAsSkillSet() {
        assertEquals(18, SkillsServiceController.getAllSkillsAsSkillSet().size());
    }
}

