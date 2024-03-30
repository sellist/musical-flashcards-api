package com.sellist.flashcards.service.cache;

import com.sellist.flashcards.model.Step;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class StepCache {
    public final Map<Integer, Step> augmentedSizeToStepMap;
    public final Map<Integer, Step> diminishedSizeToStepMap;
    public final Map<String, Integer> intervalNameToSizeMap;
    public final Map<Integer, Step> intervalSizeToStepMap;
    public final Map<Integer, Step> majorSizeToStepMap;
    public final Map<Integer, Step> minorSizeToStepMap;
    public final Map<String, Step> stepNameToStepMap;
}
