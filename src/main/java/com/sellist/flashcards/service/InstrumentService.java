package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentService {

    private final MemoryCacheProvider memoryCacheProvider;

    public InstrumentService(MemoryCacheProvider memoryCacheProvider) {
        this.memoryCacheProvider = memoryCacheProvider;
    }

    public List<Instrument> getInstruments() {
        // todo
        return null;
    }

    public Instrument getInstrument(String name) {
        // todo
        return null;
    }

    public List<Note> applyInstrumentToNotes(Instrument instrument, List<Note> notes) {
        // todo
        return null;
    }

    public List<Instrument> getInstrumentsByFamily(String family) {
        // todo
        return null;
    }

    public List<Instrument> getInstrumentsByType(String type) {
        // todo
        return null;
    }
}
