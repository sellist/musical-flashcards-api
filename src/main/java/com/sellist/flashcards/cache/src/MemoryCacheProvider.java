package com.sellist.flashcards.cache.src;

import com.sellist.flashcards.cache.NoteCache;
import com.sellist.flashcards.cache.StepCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MemoryCacheProvider {
    public final NoteCache noteCache;
    public final StepCache stepCache;
}
