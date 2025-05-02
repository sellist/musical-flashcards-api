package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.response.ResponseMetadata;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseControllerTest {

    private final BaseController baseController = new BaseController();

    @Test
    void testGenerateMetadata() {
        // Act
        ResponseMetadata metadata = baseController.generateMetadata();

        // Assert
        assertEquals("OK", metadata.getStatus());
        assertEquals(200, metadata.getCode());
        assertEquals("Success", metadata.getMessage());
    }

    @Test
    void testHandleException() {
        ResponseMetadata metadata = baseController.handleException(new Exception("Test error"));

        assertEquals("ERROR", metadata.getStatus());
        assertEquals(500, metadata.getCode());
        assertEquals("Test error", metadata.getMessage());
    }
}