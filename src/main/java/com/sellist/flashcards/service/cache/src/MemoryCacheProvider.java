package com.sellist.flashcards.service.cache.src;

import com.sellist.flashcards.service.cache.NoteCache;
import com.sellist.flashcards.service.cache.StepCache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MemoryCacheProvider {
    public final NoteCache noteCache;
    public final StepCache stepCache;
}
