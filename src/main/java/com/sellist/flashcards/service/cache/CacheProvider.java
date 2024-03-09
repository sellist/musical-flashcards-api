package com.sellist.flashcards.service.cache;

import com.sellist.flashcards.model.Step;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CacheProvider {

    public final Map<String, Integer> naturalNameToMidiMap;
    public final Map<String, Integer> flatNameToMidiMap;
    public final Map<String, Integer> sharpNameToMidiMap;
    public final Map<String, Integer> intervalNameToSizeMap;
    public final Map<Integer, Step> intervalSizeToStepMap;
    public final Map<Integer, Step> majorSizeToStepMap;
    public final Map<Integer, Step> minorSizeToStepMap;

    public CacheProvider(Map<String, Integer> naturalNameToMidiMap,
                         Map<String, Integer> flatNameToMidiMap,
                         Map<String, Integer> sharpNameToMidiMap,
                         Map<String, Integer> intervalNameToSizeMap,
                         Map<Integer, Step> intervalSizeToStepMap,
                         Map<Integer, Step> majorSizeToStepMap,
                         Map<Integer, Step> minorSizeToStepMap) {
        this.naturalNameToMidiMap = naturalNameToMidiMap;
        this.flatNameToMidiMap = flatNameToMidiMap;
        this.sharpNameToMidiMap = sharpNameToMidiMap;
        this.intervalNameToSizeMap = intervalNameToSizeMap;
        this.intervalSizeToStepMap = intervalSizeToStepMap;
        this.majorSizeToStepMap = majorSizeToStepMap;
        this.minorSizeToStepMap = minorSizeToStepMap;
    }
}
