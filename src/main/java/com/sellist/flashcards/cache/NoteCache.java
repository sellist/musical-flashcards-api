package com.sellist.flashcards.cache;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class NoteCache {
    public final Map<String, Integer> naturalNameToMidi;
    public final Map<String, Integer> flatNameToMidi;
    public final Map<String, Integer> sharpNameToMidi;
    public final Map<Integer, String> midiToSharpName;
    public final Map<Integer, String> midiToFlatName;
    public final Map<Integer, String> midiToNaturalName;
    public final Map<Integer, String> midiToDoubleFlatName;
    public final Map<Integer, String> midiToDoubleSharpName;
    public final Map<String, Integer> doubleFlatNameToMidi;
    public final Map<String, Integer> doubleSharpNameToMidi;
    public final Map<String, String> sequentialScaleNameToPattern;
    public final Map<String, String> scaleNameToScaleDegrees;
}
