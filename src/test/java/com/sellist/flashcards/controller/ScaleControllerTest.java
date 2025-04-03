package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.model.request.ScaleRequest;
import com.sellist.flashcards.model.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ScaleControllerTest {

    @Autowired
    private ScaleController scaleController;

    @Test
    void testGetMajorScaleFromRequest() {
        // Create a sample ScaleRequest object
        ScaleRequest scaleRequest = new ScaleRequest();
        scaleRequest.setScaleTonic("C4");
        scaleRequest.setScaleName("Major");
        scaleRequest.setOctaves(2);

        // Call the getScale method
        ApiResponse<Scale> response = scaleController.getScale(scaleRequest);

        // Assert the response
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("C", response.getData().get(0).getNoteName());
        assertEquals(60, response.getData().get(0).getMidiValue());
        assertEquals(15, response.getData().size());
    }

    @Test
    void testGetMinorScaleFromRequest() {
        // Create a sample ScaleRequest object
        ScaleRequest scaleRequest = new ScaleRequest();
        scaleRequest.setScaleTonic("A4");
        scaleRequest.setScaleName("Minor");
        scaleRequest.setOctaves(2);

        // Call the getScale method
        ApiResponse<Scale> response = scaleController.getScale(scaleRequest);

        // Assert the response
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("A", response.getData().get(0).getNoteName());
        assertEquals(69, response.getData().get(0).getMidiValue());
        assertEquals(15, response.getData().size());
    }

    @Test
    void testGetSharpChromaticScaleFromRequest() {
        // Create a sample ScaleRequest object
        ScaleRequest scaleRequest = new ScaleRequest();
        scaleRequest.setScaleTonic("C4");
        scaleRequest.setScaleName("flat_chromatic");
        scaleRequest.setOctaves(1);

        // Call the getScale method
        ApiResponse<Scale> response = scaleController.getScale(scaleRequest);

        // Assert the response
        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("C", response.getData().get(0).getNoteName());
        assertEquals(60, response.getData().get(0).getMidiValue());
        assertEquals(72, response.getData().get(12).getMidiValue());
        assertEquals(13, response.getData().size());
    }

}
