package com.sellist.flashcards.cache;

import com.sellist.flashcards.model.Step;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class StepCache {
    public final Map<Integer, Step> augmentedSizeToStep;
    public final Map<Integer, Step> diminishedSizeToStep;
    public final Map<String, Integer> intervalNameToSize;
    public final Map<Integer, Step> intervalSizeToStep;
    public final Map<Integer, Step> majorSizeToStep;
    public final Map<Integer, Step> minorSizeToStep;
    public final Map<String, Step> stepNameToStep;
    public final Map<String, Step> scaleDegreeToStepFromTonic;
}
