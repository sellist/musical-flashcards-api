package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.ScaleRequest;
import com.sellist.flashcards.service.ScaleService;
import com.sellist.flashcards.service.cache.src.MemoryCacheProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/scale")
public class ScaleController extends BaseController {

    ScaleService scaleService;
    MemoryCacheProvider memoryCacheProvider;

    public ScaleController(ScaleService scaleService, MemoryCacheProvider memoryCacheProvider) {
        this.scaleService = scaleService;
        this.memoryCacheProvider = memoryCacheProvider;
    }

    @PostMapping("/scale")
    public ApiResponse<List<Note>> getScale(@RequestBody ScaleRequest req) {
        return ApiResponse.<List<Note>>builder()
                .data(scaleService.generateScale(scaleService.getScalePattern(req.getScaleType()),
                        req.getTonic(),
                        req.getOctave()))
                .metadata(generateMetadata())
                .build();
    }
}
