package com.sellist.flashcards.cache;

import com.sellist.flashcards.model.Step;

import java.util.List;

public interface MusiCache {

    String midiToSharpASPN(int midiValue);

    String midiToFlatASPN(int midiValue);

    String midiToDoubleSharpASPN(int midiValue);

    String midiToDoubleFlatASPN(int midiValue);

    String midiToNaturalASPN(int midiValue);

    int flatASPNToMidi(String note);

    int sharpASPNToMidi(String note);

    int doubleFlatASPNToMidi(String note);

    int doubleSharpASPNToMidi(String note);

    int naturalASPNToMidi(String note);

    String sequentialScaleNameToPattern(String scaleName);

    Step stepNameToStep(String stepName);

    Step intervalSizeToStep(int size);

    Step scaleDegreeToStepFromTonic(String scaleDegree);

    List<Step> scaleNameToScaleDegrees(String scaleName);

    List<String> availableSteps();

    List<String> availableScales();
}
