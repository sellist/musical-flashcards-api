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
        output.setNotes(noteService.listAvailable());
        output.setScales(scaleService.listAvailable());
        output.setSteps(stepService.listAvailable());
        output.setInstruments(instrumentService.listAvailable());
        return output;
    }

    public List<String> listScales() {
        return scaleService.listAvailable();
    }

    public List<String> listNotes() {
        return noteService.listAvailable();
    }

    public List<String> listSteps() {
        return stepService.listAvailable();
    }

    public List<String> listInstruments() {
        return instrumentService.listAvailable();
    }
}
