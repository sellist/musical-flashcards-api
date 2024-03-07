package com.sellist.flashcards.constants;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StepsTests {

    @Autowired
    private StepsConstants steps;

    @Test
    void testGetSteps() {
        System.out.println(steps.getSteps());
    }
}
