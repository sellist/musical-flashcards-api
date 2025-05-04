package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.Scale;
import com.sellist.flashcards.model.request.ScaleRequest;
import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.service.ScaleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping(value = "/scale", consumes = "application/json", produces = "application/json")
    public ApiResponse<Scale> getScale(@RequestBody ScaleRequest req) {
        return ApiResponse.<Scale>builder()
                .data(scaleService.generateScale(req))
                .metadata(generateMetadata())
                .build();
    }
}
