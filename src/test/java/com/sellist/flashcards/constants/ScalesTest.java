package com.sellist.flashcards.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScalesTest {

    @Test
    void testGetScaleField() {
        String scale = Scales.getScale("MAJOR");
        Assertions.assertEquals("1,2,3,4,5,6,7", scale);

        scale = Scales.getScale("major");
        Assertions.assertEquals("1,2,3,4,5,6,7", scale);

        scale = Scales.getScale("MINOR");
        Assertions.assertEquals("1,2,b3,4,5,b6,b7", scale);

        scale = Scales.getScale("whole_tone");
        Assertions.assertEquals("1,2,3,#4,#5,#6", scale);
    }
}
