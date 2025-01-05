package com.sellist.flashcards.service;

import com.sellist.flashcards.cache.src.MemoryCacheProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheTests {

    @Autowired
    MemoryCacheProvider cacheProvider;

    @Test
    void testCacheProvider() {
        assert cacheProvider != null;
    }

    @Test
    void testGetFlatMidiValue() {
        Assertions.assertEquals(58, cacheProvider.noteCache.flatNameToMidi.get("Bb3"));
        Assertions.assertEquals(56, cacheProvider.noteCache.flatNameToMidi.get("Ab3"));
        Assertions.assertEquals(59, cacheProvider.noteCache.flatNameToMidi.get("Cb4"));
        Assertions.assertEquals(61, cacheProvider.noteCache.flatNameToMidi.get("Db4"));
        Assertions.assertEquals(63, cacheProvider.noteCache.flatNameToMidi.get("Eb4"));
        Assertions.assertEquals(64, cacheProvider.noteCache.flatNameToMidi.get("Fb4"));
        Assertions.assertEquals(66, cacheProvider.noteCache.flatNameToMidi.get("Gb4"));
        Assertions.assertEquals(68, cacheProvider.noteCache.flatNameToMidi.get("Ab4"));
        Assertions.assertEquals(70, cacheProvider.noteCache.flatNameToMidi.get("Bb4"));
    }

    @Test
    void testGetSharpMidiValue() {
        Assertions.assertEquals(61, cacheProvider.noteCache.sharpNameToMidi.get("C#4"));
        Assertions.assertEquals(63, cacheProvider.noteCache.sharpNameToMidi.get("D#4"));
        Assertions.assertEquals(65, cacheProvider.noteCache.sharpNameToMidi.get("E#4"));
        Assertions.assertEquals(66, cacheProvider.noteCache.sharpNameToMidi.get("F#4"));
        Assertions.assertEquals(68, cacheProvider.noteCache.sharpNameToMidi.get("G#4"));
        Assertions.assertEquals(70, cacheProvider.noteCache.sharpNameToMidi.get("A#4"));
        Assertions.assertEquals(72, cacheProvider.noteCache.sharpNameToMidi.get("B#4"));
        Assertions.assertEquals(73, cacheProvider.noteCache.sharpNameToMidi.get("C#5"));
    }

    @Test
    void testGetNaturalMidiValue() {
        Assertions.assertEquals(60, cacheProvider.noteCache.naturalNameToMidi.get("C4"));
        Assertions.assertEquals(72, cacheProvider.noteCache.naturalNameToMidi.get("C5"));
        Assertions.assertEquals(24, cacheProvider.noteCache.naturalNameToMidi.get("C1"));
        Assertions.assertEquals(55, cacheProvider.noteCache.naturalNameToMidi.get("G3"));
        Assertions.assertEquals(67, cacheProvider.noteCache.naturalNameToMidi.get("G4"));
        Assertions.assertEquals(79, cacheProvider.noteCache.naturalNameToMidi.get("G5"));
        Assertions.assertEquals(48, cacheProvider.noteCache.naturalNameToMidi.get("C3"));
    }
}
