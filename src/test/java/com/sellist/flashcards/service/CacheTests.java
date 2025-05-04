package com.sellist.flashcards.service;

import com.sellist.flashcards.cache.src.MemoryCacheProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CacheTests {

    @Autowired
    MemoryCacheProvider cacheProvider;
    @Autowired
    private StepService stepService;

    @Test
    void testCacheProvider() {
        Assertions.assertNotNull(cacheProvider);
    }

    @Test
    void testGetFlatMidiValue() {
        Assertions.assertEquals(58, cacheProvider.flatNameToMidi.get("Bb3"));
        Assertions.assertEquals(56, cacheProvider.flatNameToMidi.get("Ab3"));
        Assertions.assertEquals(59, cacheProvider.flatNameToMidi.get("Cb4"));
        Assertions.assertEquals(61, cacheProvider.flatNameToMidi.get("Db4"));
        Assertions.assertEquals(63, cacheProvider.flatNameToMidi.get("Eb4"));
        Assertions.assertEquals(64, cacheProvider.flatNameToMidi.get("Fb4"));
        Assertions.assertEquals(66, cacheProvider.flatNameToMidi.get("Gb4"));
        Assertions.assertEquals(68, cacheProvider.flatNameToMidi.get("Ab4"));
        Assertions.assertEquals(70, cacheProvider.flatNameToMidi.get("Bb4"));
    }

    @Test
    void testGetSharpMidiValue() {
        Assertions.assertEquals(61, cacheProvider.sharpNameToMidi.get("C#4"));
        Assertions.assertEquals(63, cacheProvider.sharpNameToMidi.get("D#4"));
        Assertions.assertEquals(65, cacheProvider.sharpNameToMidi.get("E#4"));
        Assertions.assertEquals(66, cacheProvider.sharpNameToMidi.get("F#4"));
        Assertions.assertEquals(68, cacheProvider.sharpNameToMidi.get("G#4"));
        Assertions.assertEquals(70, cacheProvider.sharpNameToMidi.get("A#4"));
        Assertions.assertEquals(72, cacheProvider.sharpNameToMidi.get("B#4"));
        Assertions.assertEquals(73, cacheProvider.sharpNameToMidi.get("C#5"));
    }

    @Test
    void testGetNaturalMidiValue() {
        Assertions.assertEquals(60, cacheProvider.naturalNameToMidi.get("C4"));
        Assertions.assertEquals(72, cacheProvider.naturalNameToMidi.get("C5"));
        Assertions.assertEquals(24, cacheProvider.naturalNameToMidi.get("C1"));
        Assertions.assertEquals(55, cacheProvider.naturalNameToMidi.get("G3"));
        Assertions.assertEquals(67, cacheProvider.naturalNameToMidi.get("G4"));
        Assertions.assertEquals(79, cacheProvider.naturalNameToMidi.get("G5"));
        Assertions.assertEquals(48, cacheProvider.naturalNameToMidi.get("C3"));
    }

    @Test
    void testGetScaleStepFromDegreeFromTonic() {

        System.out.println(cacheProvider.scaleDegreeToStepFromTonic);

        Assertions.assertEquals(
                cacheProvider.stepNameToStep.get("M2"),
                cacheProvider.scaleDegreeToStepFromTonic.get("2"));

    }
}
