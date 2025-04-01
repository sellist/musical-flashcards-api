package com.sellist.flashcards.cache;

import com.sellist.flashcards.cache.src.MemoryCacheProvider;
import com.sellist.flashcards.model.Step;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "flashcards.cache.memory.enabled", havingValue = "true")
public class MemoryMusiCacheImpl implements MusiCache {

    private final MemoryCacheProvider cache;

    public MemoryMusiCacheImpl(MemoryCacheProvider cache) {
        this.cache = cache;
    }

    public String midiToSharpASPN(int midiValue) {
        return cache.noteCache.midiToSharpName.get(midiValue);
    }

    public String midiToFlatASPN(int midiValue) {
        return cache.noteCache.midiToFlatName.get(midiValue);
    }

    public String midiToDoubleSharpASPN(int midiValue) {
        return cache.noteCache.midiToDoubleSharpName.get(midiValue);
    }

    public String midiToDoubleFlatASPN(int midiValue) {
        return cache.noteCache.midiToDoubleFlatName.get(midiValue);
    }

    public String midiToNaturalASPN(int midiValue) {
        return cache.noteCache.midiToNaturalName.get(midiValue);
    }

    public String sequentialScaleNameToPattern(String scaleName) {return cache.noteCache.sequentialScaleNameToPattern.get(scaleName.toLowerCase());}

    public int flatASPNToMidi(String note) {
        return cache.noteCache.flatNameToMidi.get(note);
    }

    public int sharpASPNToMidi(String note) {
        return cache.noteCache.sharpNameToMidi.get(note);
    }

    public int doubleFlatASPNToMidi(String note) {
        return cache.noteCache.doubleFlatNameToMidi.get(note);
    }

    public int doubleSharpASPNToMidi(String note) {
        return cache.noteCache.doubleSharpNameToMidi.get(note);
    }

    public int naturalASPNToMidi(String note) {
        return cache.noteCache.naturalNameToMidi.get(note);
    }

    public Step stepNameToStep(String stepName) {
        return cache.stepCache.stepNameToStep.get(stepName);
    }

    public List<String> availableSteps() {return cache.stepCache.stepNameToStep.keySet().stream().filter(x -> x.length() > 2).toList();}

    public Step intervalSizeToStep(int intervalSize) {
        return cache.stepCache.intervalSizeToStep.get(intervalSize);
    }

    public Step scaleDegreeToStepFromTonic(String scaleDegree) {return cache.stepCache.scaleDegreeToStepFromTonic.get(scaleDegree);}

    public List<Step> scaleNameToScaleDegrees(String scaleName) {

        List<String> degreeNotes = cache.noteCache.scaleNameToScaleDegrees.get(scaleName);
        List<Step> steps = new ArrayList<>();
        for (String degree : degreeNotes) {
            steps.add(cache.stepCache.scaleDegreeToStepFromTonic.get(degree));
        }

        return steps;
    }

    public List<String> availableScales() {
        List<String> availableScales = new ArrayList<>();
        availableScales.addAll(cache.noteCache.sequentialScaleNameToPattern.keySet().stream().toList());
        availableScales.addAll(cache.noteCache.scaleNameToScaleDegrees.keySet().stream().toList());

        return availableScales;
    }
}
