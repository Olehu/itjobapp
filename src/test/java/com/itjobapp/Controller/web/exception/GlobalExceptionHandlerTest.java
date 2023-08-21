package com.itjobapp.Controller.web.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleException() {
        Exception ex = new Exception("Test Exception");

        ModelAndView result = globalExceptionHandler.handleException(ex);

        assertEquals("error", result.getViewName());
        assertEquals("Other exception occurred: [Test Exception]", result.getModel().get("errorMessage"));
    }

    @Test
    void testHandleNoResourceFound() {
        NotFoundException ex = new NotFoundException("Test NotFoundException");

        ModelAndView result = globalExceptionHandler.handleNoResourceFound(ex);

        assertEquals("error", result.getViewName());
        assertEquals("Could not find a resource: [Test NotFoundException]", result.getModel().get("errorMessage"));
    }

    @Test
    void testHandleProcessingException() {
        ProcessingException ex = new ProcessingException("Test ProcessingException");

        ModelAndView result = globalExceptionHandler.handleException(ex);

        assertEquals("error", result.getViewName());
        assertEquals("Processing exception occurred: [Test ProcessingException]", result.getModel().get("errorMessage"));
    }

    @Test
    void testHandleBindException() {
        BindException ex = new BindException(new Object(), "testObject");
        FieldError fieldError = new FieldError("testObject", "testField", null);
        ex.addError(fieldError);

        ModelAndView result = globalExceptionHandler.handleException(ex);

        assertEquals("error", result.getViewName());
        assertEquals("Bad request for field: [testField], wrong value: [null]", result.getModel().get("errorMessage"));
    }

}
