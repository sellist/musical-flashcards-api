package com.sellist.flashcards.service;

import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
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
        Assertions.assertEquals(58, cacheProvider.noteCache.flatNameToMidiMap.get("Bb3"));
        Assertions.assertEquals(56, cacheProvider.noteCache.flatNameToMidiMap.get("Ab3"));
        Assertions.assertEquals(59, cacheProvider.noteCache.flatNameToMidiMap.get("Cb4"));
        Assertions.assertEquals(61, cacheProvider.noteCache.flatNameToMidiMap.get("Db4"));
        Assertions.assertEquals(63, cacheProvider.noteCache.flatNameToMidiMap.get("Eb4"));
        Assertions.assertEquals(64, cacheProvider.noteCache.flatNameToMidiMap.get("Fb4"));
        Assertions.assertEquals(66, cacheProvider.noteCache.flatNameToMidiMap.get("Gb4"));
        Assertions.assertEquals(68, cacheProvider.noteCache.flatNameToMidiMap.get("Ab4"));
        Assertions.assertEquals(70, cacheProvider.noteCache.flatNameToMidiMap.get("Bb4"));
    }

    @Test
    void testGetSharpMidiValue() {
        Assertions.assertEquals(61, cacheProvider.noteCache.sharpNameToMidiMap.get("C#4"));
        Assertions.assertEquals(63, cacheProvider.noteCache.sharpNameToMidiMap.get("D#4"));
        Assertions.assertEquals(65, cacheProvider.noteCache.sharpNameToMidiMap.get("E#4"));
        Assertions.assertEquals(66, cacheProvider.noteCache.sharpNameToMidiMap.get("F#4"));
        Assertions.assertEquals(68, cacheProvider.noteCache.sharpNameToMidiMap.get("G#4"));
        Assertions.assertEquals(70, cacheProvider.noteCache.sharpNameToMidiMap.get("A#4"));
        Assertions.assertEquals(72, cacheProvider.noteCache.sharpNameToMidiMap.get("B#4"));
        Assertions.assertEquals(73, cacheProvider.noteCache.sharpNameToMidiMap.get("C#5"));
    }

    @Test
    void testGetNaturalMidiValue() {
        Assertions.assertEquals(60, cacheProvider.noteCache.naturalNameToMidiMap.get("C4"));
        Assertions.assertEquals(72, cacheProvider.noteCache.naturalNameToMidiMap.get("C5"));
        Assertions.assertEquals(24, cacheProvider.noteCache.naturalNameToMidiMap.get("C1"));
        Assertions.assertEquals(55, cacheProvider.noteCache.naturalNameToMidiMap.get("G3"));
        Assertions.assertEquals(67, cacheProvider.noteCache.naturalNameToMidiMap.get("G4"));
        Assertions.assertEquals(79, cacheProvider.noteCache.naturalNameToMidiMap.get("G5"));
        Assertions.assertEquals(48, cacheProvider.noteCache.naturalNameToMidiMap.get("C3"));
    }
}
