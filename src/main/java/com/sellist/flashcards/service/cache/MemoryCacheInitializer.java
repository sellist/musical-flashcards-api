package com.sellist.flashcards.service.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.model.MidiNote;
import com.sellist.flashcards.service.ScaleService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class MemoryCacheInitializer {

    private ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    private static final Map<String, Integer> flatsNameToMidiMap = flatsNameToMidiMap();

    @PostConstruct
    public void loadCaches() {
        objectMapper.findAndRegisterModules();
    }

    @Bean(name = "instrumentMap")
    public Map<String, Instrument> instrumentsCache() {
        Map<String,Instrument> instrumentMap = new HashMap<>();

        File directory = new File("src/main/resources/static/instruments");
        File[] files = directory.listFiles((pathname) -> pathname.getName().endsWith(".yaml"));
        for (File file : files) {
            try {
                Instrument instrument = objectMapper.readValue(file, Instrument.class);
                instrumentMap.put(instrument.getName(), instrument);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }   return instrumentMap;
    }

    @Bean(name = "sharpsNameToMidiMap")
    public static Map<String, Integer> sharpsNameToMidiMap() {
        String[] noteNames = {"C#", "D#", "E#","F#", "G#","A#","B#"};
        Map<String, Integer> midiNoteMap = new HashMap<>();

        int noteIndex = 4;
        int i = 20;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "C#","D#","F#","G#","A#" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += 2;
                }
                case "E#" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += 1;
                }
                case "B#" -> {
                    midiNoteMap.put(noteName + octave, i+12);
                    i += 1;
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }

    @Bean(name = "flatsNameToMidiMap")
    public static Map<String, Integer> flatsNameToMidiMap() {
        String[] noteNames = {"Cb", "Db", "Eb","Fb", "Gb","Ab","Bb"};
        Map<String, Integer> midiNoteMap = new HashMap<>();

        int noteIndex = 5;
        int i = 20;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "Cb" -> {
                    midiNoteMap.put(noteName + octave, i-12);
                    i += 2;
                }
                case "Ab","Gb","Fb","Db" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += 2;
                }
                case "Eb","Bb" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += 1;
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

        int noteIndex = 5;
        int i = 21;
        while (i < 120) {
            String noteName = noteNames[noteIndex % 7];
            int octave = (i / 12) - 1;

            switch (noteName) {
                case "C", "A", "G", "F", "D" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += 2;
                }
                case "E","B" -> {
                    midiNoteMap.put(noteName + octave, i);
                    i += 1;
                }
            }
            noteIndex++;
        }
        return midiNoteMap;
    }
}
