package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.ApiResponse;
import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.AdjustedNotesRequest;
import com.sellist.flashcards.service.InstrumentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/instrument")
public class InstrumentsController {

    private final InstrumentService instrumentService;

    public InstrumentsController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping("/{name}")
    public ApiResponse<Instrument> getInstrument(@PathVariable String name) {
        return ApiResponse.<Instrument>builder()
                .status("success")
                .code(200)
                .message("Instrument fetched successfully")
                .data(instrumentService.getInstrument(name))
                .build();
    }

    @PostMapping("/adjust")
    public ApiResponse<List<Note>> getAdjustedNotes(AdjustedNotesRequest notes) {
        return ApiResponse.<List<Note>>builder()
                .status("success")
                .code(200)
                .message("Notes adjusted successfully")
                .data(instrumentService.applyTranspositionToNotes(notes.getInstrument(), notes.getNotes()))
                .build();
    }
}
