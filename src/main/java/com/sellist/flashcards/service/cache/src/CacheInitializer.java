package com.sellist.flashcards.service.cache.src;

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
public class CacheInitializer {

    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @PostConstruct
    public void loadCaches() {
        objectMapper.findAndRegisterModules();
    }

    @Bean(name = "instrument")
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

    @Bean(name = "midiToNaturalName")
    public static Map<Integer, String> midiToNaturalName() {
        String[] noteNames = {"C", "D", "E","F", "G","A","B"};
        Map<Integer, String> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 5;
        int i = 21;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "C", "A", "G", "F", "D" -> {
                    midiNoteMap.put(i, noteName + octave);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "E","B" -> {
                    midiNoteMap.put(i, noteName + octave);
                    i += sc.HALF_STEP.getSize();
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }

    @Bean(name = "midiToFlatName")
    public static Map<Integer, String> midiToFlatName() {
        String[] noteNames = {"Cb", "Db", "Eb","Fb", "Gb","Ab","Bb"};
        Map<Integer, String> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 0;
        int i = 11;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "Cb" -> {
                    midiNoteMap.put(i-12, noteName + octave);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "Ab","Gb","Fb","Db" -> {
                    midiNoteMap.put(i, noteName + octave);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "Eb","Bb" -> {
                    midiNoteMap.put(i, noteName + octave);
                    i += sc.HALF_STEP.getSize();
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }

    @Bean(name = "midiToSharpName")
    public static Map<Integer, String> midiToSharpName() {
        String[] noteNames = {"C#", "D#", "E#","F#", "G#","A#","B#"};
        Map<Integer, String> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 0;
        int i = 13;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "C#","D#","F#","G#","A#" -> {
                    midiNoteMap.put(i, noteName + octave);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "E#" -> {
                    midiNoteMap.put(i, noteName + octave);
                    i += sc.HALF_STEP.getSize();
                }
                case "B#" -> {
                    midiNoteMap.put(i+sc.PERFECT_OCTAVE.getSize(), noteName + octave);
                    i += sc.HALF_STEP.getSize();
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }

    @Bean(name = "sharpNameToMidi")
    public static Map<String, Integer> sharpNameToMidi() {
        String[] noteNames = {"C#", "D#", "E#","F#", "G#","A#","B#"};
        Map<String, Integer> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 0;
        int i = 13;
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

    @Bean(name = "flatNameToMidi")
    public static Map<String, Integer> flatNameToMidi() {
        String[] noteNames = {"Cb", "Db", "Eb","Fb", "Gb","Ab","Bb"};
        Map<String, Integer> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 0;
        int i = 11;
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

    @Bean(name = "naturalNameToMidi")
    public static Map<String, Integer> naturalsNameToMidi() {
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

    @Bean(name = "intervalNameToSize")
    public static Map<String, Integer> intervalNameToSize() {
        List<Step> steps = new StepsConstants().getAllSteps();
        Map<String, Integer> stepNameToSizeMap = new HashMap<>();
        for (Step step : steps) {
            stepNameToSizeMap.put(step.getShortName(), step.getSize());
            stepNameToSizeMap.put(step.getStepName(), step.getSize());
        }
        return stepNameToSizeMap;
    }

    @Bean(name = "intervalSizeToStep")
    public static Map<Integer, Step> intervalSizeToStep() {
        List<Step> steps = new StepsConstants().getStandardSteps();
        Map<Integer, Step> stepSizeToNameMap = new HashMap<>();
        for (Step step : steps) {
            stepSizeToNameMap.put(step.getSize(), step);
        }
        return stepSizeToNameMap;
    }

    @Bean(name = "stepNameToStep")
    public static Map<String, Step> stepNameToStep() {
        Map<String, Step> stepNameToStepMap = new HashMap<>();
        for (Step step : new StepsConstants().getAllSteps()) {
            stepNameToStepMap.put(step.getStepName(), step);
            stepNameToStepMap.put(step.getShortName(), step);
        }
        return stepNameToStepMap;
    }

    @Bean(name = "majorSizeToStep")
    public static Map<Integer, Step> majorSizeToStep() {
        Map<Integer, Step> majorStepsMap = new HashMap<>();
        for (Step step : new StepsConstants().getAllSteps()) {
            if (step.getQuality().equals("M") || step.getQuality().equals("P")) {
                majorStepsMap.put(step.getSize(), step);
            }
        }
        return majorStepsMap;
    }

    @Bean(name = "minorSizeToStep")
    public static Map<Integer, Step> minorSizeToStep() {
        Map<Integer, Step> minorStepsMap = new HashMap<>();
        for (Step step : new StepsConstants().getAllSteps()) {
            if (step.getQuality().equals("m")) {
                minorStepsMap.put(step.getSize(), step);
            }
        }
        return minorStepsMap;
    }

    @Bean(name = "diminishedSizeToStep")
    public static Map<Integer, Step> diminishedSizeToStep() {
        Map<Integer, Step> diminishedStepsMap = new HashMap<>();
        for (Step step : new StepsConstants().getAllSteps()) {
            if (step.getQuality().equals("d")) {
                diminishedStepsMap.put(step.getSize(), step);
            }
        }
        return diminishedStepsMap;
    }

    @Bean(name = "augmentedSizeToStep")
    public static Map<Integer, Step> augmentedSizeToStep() {
        Map<Integer, Step> augmentedStepsMap = new HashMap<>();
        for (Step step : new StepsConstants().getAllSteps()) {
            if (step.getQuality().equals("A")) {
                augmentedStepsMap.put(step.getSize(), step);
            }
        }
        return augmentedStepsMap;
    }
}
