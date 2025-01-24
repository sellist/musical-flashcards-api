package com.sellist.flashcards.cache.src;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sellist.flashcards.constants.StepsConstants;
import com.sellist.flashcards.model.Step;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
public class CacheInitializer {

    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @PostConstruct
    public void loadCaches() {
        objectMapper.findAndRegisterModules();
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

    @Bean(name = "midiToDoubleSharpName")
    public static Map<Integer, String> midiToDoubleSharpName() {
        String[] noteNames = {"C##", "D##", "E##","F##", "G##","A##","B##"};
        Map<Integer, String> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 0;
        int i = 14;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "C##","D##","F##","G##","A##" -> {
                    midiNoteMap.put(i, noteName + octave);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "E##" -> {
                    midiNoteMap.put(i, noteName + octave);
                    i += sc.HALF_STEP.getSize();
                }
                case "B##" -> {
                    midiNoteMap.put(i+sc.PERFECT_OCTAVE.getSize(), noteName + octave);
                    i += sc.HALF_STEP.getSize();
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }

    @Bean(name = "midiToDoubleFlatName")
    public static Map<Integer, String> midiToDoubleFlatName() {
        String[] noteNames = {"Cbb", "Dbb", "Ebb","Fbb", "Gbb","Abb","Bbb"};
        Map<Integer, String> midiNoteMap = new HashMap<>();
        StepsConstants sc = new StepsConstants();

        int noteIndex = 0;
        int i = 10;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "Cbb" -> {
                    midiNoteMap.put(i-12, noteName + octave);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "Abb","Gbb","Fbb","Dbb" -> {
                    midiNoteMap.put(i, noteName + octave);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "Ebb","Bbb" -> {
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
        Map<String, Integer> midiNoteMap =  new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
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
        Map<String, Integer> midiNoteMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
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

    @Bean(name = "doubleSharpNameToMidi")
    public static Map<String, Integer> doubleSharpNameToMidi() {
        String[] noteNames = {"C##", "D##", "E##","F##", "G##","A##","B##"};
        Map<String, Integer> midiNoteMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        StepsConstants sc = new StepsConstants();

        int noteIndex = 0;
        int i = 14;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "C##","D##","F##","G##","A##" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "E##" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += sc.HALF_STEP.getSize();
                }
                case "B##" -> {
                    midiNoteMap.put(noteName + octave, i+sc.PERFECT_OCTAVE.getSize());
                    i += sc.HALF_STEP.getSize();
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }

    @Bean(name = "doubleFlatNameToMidi")
    public static Map<String, Integer> doubleFlatNameToMidi() {
        String[] noteNames = {"Cbb", "Dbb", "Ebb","Fbb", "Gbb","Abb","Bbb"};
        Map<String, Integer> midiNoteMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        StepsConstants sc = new StepsConstants();

        int noteIndex = 0;
        int i = 10;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "Cbb" -> {
                    midiNoteMap.put(noteName + octave, i-12);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "Abb","Gbb","Fbb","Dbb" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += sc.WHOLE_STEP.getSize();
                }
                case "Ebb","Bbb" -> {
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
        Map<String, Integer> midiNoteMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
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

    @Bean(name = "sequentialScaleNameToPattern")
    public Map<String, String> sequentialScaleNameToPattern() {
        Map<String,String> rawMap;
        Map<String, String> outputMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/sequentialScales.yaml");
            rawMap = mapper.readValue(inputStream, Map.class);
        } catch (Exception ignored) {
            return outputMap;
        }

        for (Map.Entry<String, String> entry : rawMap.entrySet()) {
            outputMap.put(entry.getKey().toLowerCase(), entry.getValue().trim());
        }
        return outputMap;
    }

    @Bean(name = "scaleNameToScaleDegrees")
    public Map<String, String> scaleNameToScaleDegrees() {
        Map<String,String> rawMap;
        Map<String, String> outputMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/scaleDegrees.yaml");
            rawMap = mapper.readValue(inputStream, Map.class);
        } catch (Exception ignored) {
            return outputMap;
        }

        for (Map.Entry<String, String> entry : rawMap.entrySet()) {
            outputMap.put(entry.getKey().toLowerCase(), entry.getValue().trim());
        }
        return outputMap;
    }

    @Bean(name = "scaleDegreeToStepFromTonic")
    public Map<String, Step> scaleDegreeToStepFromTonic() {
        Map<String, Step> rawMap;
        Map<String, Step> outputMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/scaleDegreeSteps.yaml");
            rawMap = mapper.readValue(inputStream, Map.class);
        } catch (Exception ignored) {
            return outputMap;
        }

        for (Map.Entry<String, Step> entry : rawMap.entrySet()) {
            outputMap.put(entry.getKey().toLowerCase(), entry.getValue());
        }

        System.out.println(outputMap);
        for (Map.Entry<String, Step> entry : outputMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return outputMap;
    }
}
