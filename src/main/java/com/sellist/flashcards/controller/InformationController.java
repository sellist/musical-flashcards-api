package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.FormInformation;
import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.service.InfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/info")
public class InformationController extends BaseController {

    private final InfoService infoService;

    public InformationController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/")
    public ApiResponse<FormInformation> getInfo() {
        return ApiResponse.<FormInformation>builder()
                .data(infoService.getInfo())
                .metadata(generateMetadata())
                .build();
    }

}
