package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.model.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ScaleControllerTest {
    @Autowired
    private ScaleController scaleController;

    @Test
    void testGetMajorScaleFromRequest() {
        ApiResponse<Scale> response = scaleController.getScale("C4", "Major", 2);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("C", response.getData().get(0).getNoteName());
        assertEquals(60, response.getData().get(0).getMidiValue());
        assertEquals(15, response.getData().size());
    }

    @Test
    void testGetMinorScaleFromRequest() {

        ApiResponse<Scale> response = scaleController.getScale("A4","Minor",2);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("A", response.getData().get(0).getNoteName());
        assertEquals(69, response.getData().get(0).getMidiValue());
        assertEquals(15, response.getData().size());
    }

    @Test
    void testGetSharpChromaticScaleFromRequest() {
        ApiResponse<Scale> response = scaleController.getScale("C4","flat_chromatic",1);

        assertNotNull(response);
        assertNotNull(response.getData());
        assertEquals("C", response.getData().get(0).getNoteName());
        assertEquals(60, response.getData().get(0).getMidiValue());
        assertEquals(72, response.getData().get(12).getMidiValue());
        assertEquals(13, response.getData().size());
    }
}