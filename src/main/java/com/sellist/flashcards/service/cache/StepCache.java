package com.sellist.flashcards.service.cache;

import com.sellist.flashcards.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StepCache {
    public final Map<Integer, Step> augmentedSizeToStepMap;
    public final Map<Integer, Step> diminishedSizeToStepMap;
    public final Map<String, Integer> intervalNameToSizeMap;
    public final Map<Integer, Step> intervalSizeToStepMap;
    public final Map<Integer, Step> majorSizeToStepMap;
    public final Map<Integer, Step> minorSizeToStepMap;
    public final Map<String, Step> stepNameToStepMap;

    @Autowired
    public StepCache(Map<Integer, Step> augmentedSizeToStepMap, Map<Integer, Step> diminishedSizeToStepMap, Map<String, Integer> intervalNameToSizeMap, Map<Integer, Step> intervalSizeToStepMap, Map<Integer, Step> majorSizeToStepMap, Map<Integer, Step> minorSizeToStepMap, Map<String, Step> stepNameToStepMap) {
        this.augmentedSizeToStepMap = augmentedSizeToStepMap;
        this.diminishedSizeToStepMap = diminishedSizeToStepMap;
        this.intervalNameToSizeMap = intervalNameToSizeMap;
        this.intervalSizeToStepMap = intervalSizeToStepMap;
        this.majorSizeToStepMap = majorSizeToStepMap;
        this.minorSizeToStepMap = minorSizeToStepMap;
        this.stepNameToStepMap = stepNameToStepMap;
    }
}
