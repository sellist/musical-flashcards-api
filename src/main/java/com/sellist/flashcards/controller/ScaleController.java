package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.model.request.ScaleRequest;
import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.service.ScaleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/scale")
public class ScaleController extends BaseController {

    final ScaleService scaleService;

    public ScaleController(ScaleService scaleService) {
        this.scaleService = scaleService;
    }

    @GetMapping("/scale")
    public ApiResponse<Scale> getScale(
            @RequestParam String scaleTonic,
            @RequestParam String scaleName,
            @RequestParam int octaves) {
        ScaleRequest req = new ScaleRequest();
        req.setScaleTonic(scaleTonic);
        req.setScaleName(scaleName);
        req.setOctaves(octaves);
        return ApiResponse.<Scale>builder()
                .data(scaleService.generateScale(req))
                .metadata(generateMetadata())
                .build();
    }
}
