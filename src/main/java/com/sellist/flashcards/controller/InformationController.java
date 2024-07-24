package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.ApiResponse;
import com.sellist.flashcards.model.FormInformation;
import com.sellist.flashcards.service.InfoService;
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

    private final InfoService infoService;

    public InformationController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/form")
    public ApiResponse<FormInformation> getFormInfo() {
        return ApiResponse.<FormInformation>builder()
                .status("success")
                .code(200)
                .message("Info fetched successfully")
                .data(infoService.getInfo())
                .build();
    }

    @GetMapping("/scales")
    public ApiResponse<List<String>> getScales() {
        return ApiResponse.<List<String>>builder()
                .status("success")
                .code(200)
                .message("Scales fetched successfully")
                .data(infoService.getScales())
                .build();
    }

    @GetMapping("/instruments")
    public ApiResponse<List<String>> getInstruments() {
        return ApiResponse.<List<String>>builder()
                .status("success")
                .code(200)
                .message("Instruments fetched successfully")
                .data(infoService.getInstruments())
                .build();
    }

    @GetMapping("/notes")
    public ApiResponse<List<String>> getNotes() {
        return ApiResponse.<List<String>>builder()
                .status("success")
                .code(200)
                .message("Notes fetched successfully")
                .data(infoService.getNotes())
                .build();
    }

    @GetMapping("/steps")
    public ApiResponse<List<String>> getSteps() {
        return ApiResponse.<List<String>>builder()
                .status("success")
                .code(200)
                .message("Steps fetched successfully")
                .data(infoService.getSteps())
                .build();
    }
}
