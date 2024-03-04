package com.sellist.flashcards.service.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sellist.flashcards.model.Instrument;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.HashMap;
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
        String[] noteNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        Map<String, Integer> midiNoteMap = new HashMap<>();

        for (int i = 24; i <= 120; i++) {
            String noteName = noteNames[i % 12];
            int octave = (i / 12) - 1;
            midiNoteMap.put(noteName + octave,i);
        }

        return midiNoteMap;
    }

    @Bean(name = "flatsNameToMidiMap")
    public static Map<String, Integer> flatsNameToMidiMap() {
        String[] noteNamesWithFlats = {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
        Map<String, Integer> noteMidiMap = new HashMap<>();

        for (int i = 24; i <= 120; i++) {
            int octave = (i / 12) - 1;
            String noteNameWithFlat = noteNamesWithFlats[i % 12] + octave;

            noteMidiMap.put(noteNameWithFlat, i);
        }

        return noteMidiMap;
    }

}
