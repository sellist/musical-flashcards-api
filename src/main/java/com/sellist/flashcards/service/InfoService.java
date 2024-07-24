package com.sellist.flashcards.service;

import com.sellist.flashcards.model.FormInformation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {
    private final NoteService noteService;
    private final ScaleService scaleService;
    private final InstrumentService instrumentService;
    private final StepService stepService;

    public InfoService(NoteService noteService, ScaleService scaleService, InstrumentService instrumentService, StepService stepService) {
        this.noteService = noteService;
        this.scaleService = scaleService;
        this.instrumentService = instrumentService;
        this.stepService = stepService;
    }

    public FormInformation getInfo() {
        FormInformation output = new FormInformation();
        output.setNotes(noteService.getAvailable());
        output.setScales(scaleService.getAvailable());
        output.setSteps(stepService.getAvailable());
        output.setInstruments(instrumentService.getAvailable());
        return output;
    }

    public List<String> getScales() {
        return scaleService.getAvailable();
    }

    public List<String> getNotes() {
        return noteService.getAvailable();
    }

    public List<String> getSteps() {
        return stepService.getAvailable();
    }

    public List<String> getInstruments() {
        return instrumentService.getAvailable();
    }
}
