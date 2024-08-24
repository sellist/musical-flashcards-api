package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.Step;
import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentService implements ProvideApiInfo {

    private final MemoryCacheProvider memoryCacheProvider;
    private final NoteService noteService;
    private final StepService stepService;

    public InstrumentService(MemoryCacheProvider memoryCacheProvider, NoteService noteService, StepService stepService) {
        this.memoryCacheProvider = memoryCacheProvider;
        this.noteService = noteService;
        this.stepService = stepService;
    }

    public Instrument getInstrument(String name) {
        return memoryCacheProvider.instrumentCache.nameToInstruments.get(name);
    }

    public List<Note> applyTranspositionToNotes(Instrument instrument, List<Note> notes) {
        Step transpositionStep = getTranspositionStep(instrument);
        for (Note note: notes) {

            if (transpositionStep.getSize() > 0) {
                note.setTransposedNote(stepService.stepUp(note,transpositionStep));
            } else if (transpositionStep.getSize() < 0) {
                note.setTransposedNote(stepService.stepDown(note, transpositionStep));
            }
        }
        return notes;
    }

    public Step getTranspositionStep(Instrument instrument) {
        return stepService.getDifference(
                noteService.generateNote("C4"),
                noteService.generateNote(instrument.getTransposition())
        );
    }

    public List<Instrument> getInstrumentsByFamily(String family) {
        return memoryCacheProvider.instrumentCache.nameToInstruments.values().stream().filter(
                instrument -> instrument.getFamily().equals(family)).toList();
    }

    @Override
    public List<String> listAvailable() {
        return memoryCacheProvider.instrumentCache.nameToInstruments.keySet().stream().toList();
    }
}
