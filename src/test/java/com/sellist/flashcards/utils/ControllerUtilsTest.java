package com.sellist.flashcards.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}

