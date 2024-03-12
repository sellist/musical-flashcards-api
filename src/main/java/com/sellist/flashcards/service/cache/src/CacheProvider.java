package com.sellist.flashcards.service.cache.src;

import com.sellist.flashcards.service.cache.NoteCache;
import com.sellist.flashcards.service.cache.StepCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheProvider {

    public final NoteCache noteCache;
    public final StepCache stepCache;

    @Autowired
    public CacheProvider(NoteCache noteCache, StepCache stepCache) {
        this.noteCache = noteCache;
        this.stepCache = stepCache;
    }
}
