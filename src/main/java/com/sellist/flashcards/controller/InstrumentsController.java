package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.ApiResponse;
import com.sellist.flashcards.model.Instrument;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.AdjustedNotesRequest;
import com.sellist.flashcards.service.InstrumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class InstrumentsController {

    private InstrumentService instrumentService;

    @GetMapping("/instruments/{family}")
    public ApiResponse<List<Instrument>> getInstrumentsByFamily(@PathVariable String family) {
        return ApiResponse.<List<Instrument>>builder()
                .status("success")
                .httpStatus(200)
                .message("Instruments fetched successfully")
                .data(instrumentService.getInstrumentsByFamily(family))
                .build();
    }

    @PostMapping
    public ApiResponse<List<Note>> getAdjustedNotes(AdjustedNotesRequest notes) {
        return ApiResponse.<List<Note>>builder()
                .status("success")
                .httpStatus(200)
                .message("Notes adjusted successfully")
                .data(instrumentService.applyInstrumentToNotes(notes.getInstrument(), notes.getNotes()))
                .build();
    }
}
