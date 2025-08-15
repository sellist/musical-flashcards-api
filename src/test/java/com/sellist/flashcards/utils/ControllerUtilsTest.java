package com.sellist.flashcards.utils;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerUtilsTest {
    @Test
    void testStandardInput() {
        List<String> result = ControllerUtils.deserialize("C4,D#5,Ab7");
        assertEquals(List.of("C4", "D#5", "Ab7"), result);
    }

    @Test
    void testUrlEncodedSharp() {
        List<String> result = ControllerUtils.deserialize("C4,D%235,Ab7");
        assertEquals(List.of("C4", "D#5", "Ab7"), result);
    }

    @Test
    void testExtraSpaces() {
        List<String> result = ControllerUtils.deserialize(" C4 , D#5 , Ab7 ");
        assertEquals(List.of("C4", "D#5", "Ab7"), result);
    }

    @Test
    void testEmptyString() {
        List<String> result = ControllerUtils.deserialize("");
        assertTrue(result.isEmpty());
    }

    @Test
    void testOnlyDelimiters() {
        List<String> result = ControllerUtils.deserialize(",,,");
        assertTrue(result.isEmpty());
    }

    @Test
    void testNullInput() {
        List<String> result = ControllerUtils.deserialize(null);
        assertTrue(result.isEmpty());
    }
}

