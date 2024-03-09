package com.sellist.flashcards.service.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sellist.flashcards.constants.StepsConstants;
import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.model.Step;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MemoryCacheInitializer {

    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @PostConstruct
    public void loadCaches() {
        objectMapper.findAndRegisterModules();
    }

    @Bean(name = "instrumentMap")
    public Map<String, Instrument> instrumentsCache() {
        Map<String,Instrument> instrumentMap = new HashMap<>();

        File directory = new File("src/main/resources/static/instruments");
        File[] files = directory.listFiles((pathname) -> pathname.getName().endsWith(".yaml"));
        assert files != null;
        for (File file : files) {
            try {
                Instrument instrument = objectMapper.readValue(file, Instrument.class);
                instrumentMap.put(instrument.getName(), instrument);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }   return instrumentMap;
    }

    @Bean(name = "sharpNameToMidiMap")
    public static Map<String, Integer> sharpNameToMidiMap() {
        String[] noteNames = {"C#", "D#", "E#","F#", "G#","A#","B#"};
        Map<String, Integer> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 4;
        int i = 20;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "C#","D#","F#","G#","A#" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "E#" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += sc.HALF_STEP.getSize();
                }
                case "B#" -> {
                    midiNoteMap.put(noteName + octave, i+sc.PERFECT_OCTAVE.getSize());
                    i += sc.HALF_STEP.getSize();
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }

    @Bean(name = "flatNameToMidiMap")
    public static Map<String, Integer> flatNameToMidiMap() {
        String[] noteNames = {"Cb", "Db", "Eb","Fb", "Gb","Ab","Bb"};
        Map<String, Integer> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 5;
        int i = 20;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "Cb" -> {
                    midiNoteMap.put(noteName + octave, i-12);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "Ab","Gb","Fb","Db" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "Eb","Bb" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += sc.HALF_STEP.getSize();
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }

    @Bean(name = "naturalNameToMidiMap")
    public static Map<String, Integer> naturalsNameToMidiMap() {
        String[] noteNames = {"C", "D", "E","F", "G","A","B"};
        Map<String, Integer> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 5;
        int i = 21;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "C", "A", "G", "F", "D" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "E","B" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += sc.HALF_STEP.getSize();
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }

    @Bean(name = "intervalNameToSizeMap")
    public static Map<String, Integer> intervalNameToSizeMap() {
        List<Step> steps = new StepsConstants().getAllSteps();
        Map<String, Integer> stepNameToSizeMap = new HashMap<>();
        for (Step step : steps) {
            stepNameToSizeMap.put(step.getShortName(), step.getSize());
            stepNameToSizeMap.put(step.getStepName(), step.getSize());
        }
        return stepNameToSizeMap;
    }

    @Bean(name = "intervalSizeToStepMap")
    public static Map<Integer, Step> intervalSizeToStepMap() {
        List<Step> steps = new StepsConstants().getStandardSteps();
        Map<Integer, Step> stepSizeToNameMap = new HashMap<>();
        for (Step step : steps) {
            stepSizeToNameMap.put(step.getSize(), step);
        }
        return stepSizeToNameMap;
    }

    @Bean(name = "majorSizeToStepMap")
    public static Map<Integer, Step> majorSizeToStepMap() {
        Map<Integer, Step> majorStepsMap = new HashMap<>();
        for (Step step : new StepsConstants().getAllSteps()) {
            if (step.getMod() == 0) {
                majorStepsMap.put(step.getSize(), step);
            }
        }
        return majorStepsMap;
    }

    @Bean(name = "minorSizeToStepMap")
    public static Map<Integer, Step> minorSizeToStepMap() {
        Map<Integer, Step> minorStepsMap = new HashMap<>();
        for (Step step : new StepsConstants().getAllSteps()) {
            if (step.getMod() == -1) {
                minorStepsMap.put(step.getSize(), step);
            }
        }
        return minorStepsMap;
    }
}
