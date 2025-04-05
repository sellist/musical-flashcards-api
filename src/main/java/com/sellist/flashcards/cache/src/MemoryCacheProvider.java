package com.sellist.flashcards.cache.src;

import com.sellist.flashcards.model.Step;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MemoryCacheProvider {
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
    public final Map<String, List<String>> scaleNameToScaleDegrees;
    public final Map<Integer, Step> augmentedSizeToStep;
    public final Map<Integer, Step> diminishedSizeToStep;
    public final Map<String, Integer> intervalNameToSize;
    public final Map<Integer, Step> intervalSizeToStep;
    public final Map<Integer, Step> majorSizeToStep;
    public final Map<Integer, Step> minorSizeToStep;
    public final Map<String, Step> stepNameToStep;
    public final Map<String, Step> scaleDegreeToStepFromTonic;

    public MemoryCacheProvider(
            Map<String, Integer> naturalNameToMidi,
            Map<String, Integer> flatNameToMidi,
            Map<String, Integer> sharpNameToMidi,
            Map<Integer, String> midiToSharpName,
            Map<Integer, String> midiToFlatName,
            Map<Integer, String> midiToNaturalName,
            Map<Integer, String> midiToDoubleFlatName,
            Map<Integer, String> midiToDoubleSharpName,
            Map<String, Integer> doubleFlatNameToMidi,
            Map<String, Integer> doubleSharpNameToMidi,
            Map<String, String> sequentialScaleNameToPattern,
            Map<String, List<String>> scaleNameToScaleDegrees,
            Map<Integer, Step> augmentedSizeToStep,
            Map<Integer, Step> diminishedSizeToStep,
            Map<String, Integer> intervalNameToSize,
            Map<Integer, Step> intervalSizeToStep,
            Map<Integer, Step> majorSizeToStep,
            Map<Integer, Step> minorSizeToStep,
            Map<String, Step> stepNameToStep,
            Map<String, Step> scaleDegreeToStepFromTonic
    ) {
        this.naturalNameToMidi = naturalNameToMidi;
        this.flatNameToMidi = flatNameToMidi;
        this.sharpNameToMidi = sharpNameToMidi;
        this.midiToSharpName = midiToSharpName;
        this.midiToFlatName = midiToFlatName;
        this.midiToNaturalName = midiToNaturalName;
        this.midiToDoubleFlatName = midiToDoubleFlatName;
        this.midiToDoubleSharpName = midiToDoubleSharpName;
        this.doubleFlatNameToMidi = doubleFlatNameToMidi;
        this.doubleSharpNameToMidi = doubleSharpNameToMidi;
        this.sequentialScaleNameToPattern = sequentialScaleNameToPattern;
        this.scaleNameToScaleDegrees = scaleNameToScaleDegrees;
        this.augmentedSizeToStep = augmentedSizeToStep;
        this.diminishedSizeToStep = diminishedSizeToStep;
        this.intervalNameToSize = intervalNameToSize;
        this.intervalSizeToStep = intervalSizeToStep;
        this.majorSizeToStep = majorSizeToStep;
        this.minorSizeToStep = minorSizeToStep;
        this.stepNameToStep = stepNameToStep;
        this.scaleDegreeToStepFromTonic = scaleDegreeToStepFromTonic;
    }
}