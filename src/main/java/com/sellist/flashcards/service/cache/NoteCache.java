package com.sellist.flashcards.service.cache;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class NoteCache {
    public final Map<String, Integer> naturalNameToMidiMap;
    public final Map<String, Integer> flatNameToMidiMap;
    public final Map<String, Integer> sharpNameToMidiMap;
    public final Map<Integer, String> midiToSharpNameMap;
    public final Map<Integer, String> midiToFlatNameMap;
    public final Map<Integer, String> midiToNaturalNameMap;
}
