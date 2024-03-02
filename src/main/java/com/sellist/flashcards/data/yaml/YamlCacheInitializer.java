package com.sellist.flashcards.data.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.service.cache.AvailableCaches;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component
public class YamlCacheInitializer {

    private ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @Value("#{'${midi.noteNames}'.split(',')}")
    private String[] notes;
    @Bean
    public void loadCaches() {
        objectMapper.findAndRegisterModules();
        loadInstrumentsCache();
    }

    public void loadInstrumentsCache() {

        File directory = new File("src/main/resources/static/instruments");
        File[] files = directory.listFiles((pathname) -> pathname.getName().endsWith(".yaml"));
        for (File file : files) {
            try {
                Instrument instrument = objectMapper.readValue(file, Instrument.class);
                System.out.println(instrument);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadNaturalNotesCache(@Value("${scales.major}") String steps,
                                      @Value("${midi.lowestNote}") String lowestNote,
                                      @Value("${midi.lowestNote.name}") String lowestNoteName,
                                      @Value("${midi.highestNote}") String highestNote) {
        Integer highestNoteInt = Integer.parseInt(highestNote);
        Integer noteInt = Integer.parseInt(lowestNote);
        String[] stepsArray = steps.split("");
        Map<String,Integer> naturalNotes = new HashMap<>();

        int noteIndex = 0;
        int noteNameIndex = 0;
        int stepIndex = 0;
        while (noteInt <= highestNoteInt) {
            naturalNotes.put(notes[noteIndex], noteInt);
            noteInt += Integer.parseInt(stepsArray[stepIndex]);

            if (noteIndex == notes.length) {
                noteIndex = 0;
            }
            if (noteNameIndex == notes.length) {
                noteNameIndex = 0;
            }
            if (stepIndex == stepsArray.length) {
                stepIndex = 0;
            }
        }
    }
}
