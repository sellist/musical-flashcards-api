package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Note;
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
    void generateScale() {
        List<Note> scale = sut.generateScale("W,W,H,W,W,W,H", "C4", 1);
        Assertions.assertEquals(8, scale.size());
        Assertions.assertEquals("C4", scale.get(0).toString());
        Assertions.assertEquals("D4", scale.get(1).toString());
        Assertions.assertEquals("E4", scale.get(2).toString());
        Assertions.assertEquals("F4", scale.get(3).toString());
        Assertions.assertEquals("G4", scale.get(4).toString());
        Assertions.assertEquals("A4", scale.get(5).toString());
        Assertions.assertEquals("B4", scale.get(6).toString());
        Assertions.assertEquals("C5", scale.get(7).toString());
    }

}
