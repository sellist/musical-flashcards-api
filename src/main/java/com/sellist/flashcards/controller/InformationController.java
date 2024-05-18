package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.ApiResponse;
import com.sellist.flashcards.service.InstrumentService;
import com.sellist.flashcards.service.NoteService;
import com.sellist.flashcards.service.ScaleService;
import com.sellist.flashcards.service.StepService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/info")
public class InformationController {

    private final NoteService noteService;
    private final InstrumentService instrumentService;
    private final ScaleService scaleService;
    private final StepService stepService;

    public InformationController(NoteService noteService, InstrumentService instrumentService, ScaleService scaleService, StepService stepService) {
        this.noteService = noteService;
        this.instrumentService = instrumentService;
        this.scaleService = scaleService;
        this.stepService = stepService;
    }

    @GetMapping("/scales")
    public ApiResponse<List<String>> getScales() {
        return ApiResponse.<List<String>>builder()
                .status("success")
                .code(200)
                .message("Scales fetched successfully")
                .data(scaleService.getAvailable())
                .build();
    }

    @GetMapping("/instruments")
    public ApiResponse<List<String>> getInstruments() {
        return ApiResponse.<List<String>>builder()
                .status("success")
                .code(200)
                .message("Instruments fetched successfully")
                .data(instrumentService.getAvailable())
                .build();
    }

    @GetMapping("/notes")
    public ApiResponse<List<String>> getNotes() {
        return ApiResponse.<List<String>>builder()
                .status("success")
                .code(200)
                .message("Notes fetched successfully")
                .data(noteService.getAvailable())
                .build();
    }

    @GetMapping("/steps")
    public ApiResponse<List<String>> getSteps() {
        return ApiResponse.<List<String>>builder()
                .status("success")
                .code(200)
                .message("Steps fetched successfully")
                .data(stepService.getAvailable())
                .build();
    }
}
