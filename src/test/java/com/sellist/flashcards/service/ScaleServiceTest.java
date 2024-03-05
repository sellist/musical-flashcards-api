package com.sellist.flashcards.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ScaleServiceTest {

    @Autowired
    private ScaleService sut;

    @Test
    public void testGetScale() {
        List<String> expected = List.of("C4", "D4", "E4", "F4", "G4", "A4", "B4", "C5");
        List<String> data = sut.generateScale("WWHWWWH", "C4", 1);
        Assertions.assertLinesMatch(expected, data);
    }

}
