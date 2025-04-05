package com.sellist.flashcards.cache;

import com.sellist.flashcards.cache.src.MemoryCacheProvider;
import com.sellist.flashcards.model.Step;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryMusiCacheImpl implements MusiCache {

    private final MemoryCacheProvider cache;

    public MemoryMusiCacheImpl(MemoryCacheProvider cache) {
        this.cache = cache;
    }

    @Override
    public String midiToSharpASPN(int midiValue) {
        return cache.midiToSharpName.get(midiValue);
    }

    @Override
    public String midiToFlatASPN(int midiValue) {
        return cache.midiToFlatName.get(midiValue);
    }

    @Override
    public String midiToDoubleSharpASPN(int midiValue) {
        return cache.midiToDoubleSharpName.get(midiValue);
    }

    @Override
    public String midiToDoubleFlatASPN(int midiValue) {
        return cache.midiToDoubleFlatName.get(midiValue);
    }

    @Override
    public String midiToNaturalASPN(int midiValue) {
        return cache.midiToNaturalName.get(midiValue);
    }

    @Override
    public String sequentialScaleNameToPattern(String scaleName) {
        return cache.sequentialScaleNameToPattern.get(scaleName.toLowerCase());
    }

    @Override
    public int flatASPNToMidi(String note) {
        return cache.flatNameToMidi.get(note);
    }

    @Override
    public int sharpASPNToMidi(String note) {
        return cache.sharpNameToMidi.get(note);
    }

    @Override
    public int doubleFlatASPNToMidi(String note) {
        return cache.doubleFlatNameToMidi.get(note);
    }

    @Override
    public int doubleSharpASPNToMidi(String note) {
        return cache.doubleSharpNameToMidi.get(note);
    }

    @Override
    public int naturalASPNToMidi(String note) {
        return cache.naturalNameToMidi.get(note);
    }

    @Override
    public Step stepNameToStep(String stepName) {
        return cache.stepNameToStep.get(stepName);
    }

    @Override
    public List<String> availableSteps() {
        return cache.stepNameToStep.keySet().stream().filter(x -> x.length() > 2).toList();
    }

    @Override
    public Step intervalSizeToStep(int intervalSize) {
        return cache.intervalSizeToStep.get(intervalSize);
    }

    @Override
    public Step scaleDegreeToStepFromTonic(String scaleDegree) {
        return cache.scaleDegreeToStepFromTonic.get(scaleDegree);
    }

    @Override
    public List<Step> scaleNameToScaleDegrees(String scaleName) {
        List<String> degreeNotes = cache.scaleNameToScaleDegrees.get(scaleName);
        List<Step> steps = new ArrayList<>();
        for (String degree : degreeNotes) {
            steps.add(cache.scaleDegreeToStepFromTonic.get(degree));
        }
        return steps;
    }

    @Override
    public List<String> availableScales() {
        List<String> availableScales = new ArrayList<>();
        availableScales.addAll(cache.sequentialScaleNameToPattern.keySet().stream().toList());
        availableScales.addAll(cache.scaleNameToScaleDegrees.keySet().stream().toList());
        return availableScales;
    }
}