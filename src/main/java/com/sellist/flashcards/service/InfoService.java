package com.sellist.flashcards.service;

import com.sellist.flashcards.model.FormInformation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {
    private final ScaleService scaleService;
    private final StepService stepService;

    public InfoService(ScaleService scaleService, StepService stepService) {
        this.scaleService = scaleService;
        this.stepService = stepService;
    }

    public FormInformation getInfo() {
        FormInformation output = new FormInformation();
        output.setScales(scaleService.listAvailable());
        output.setSteps(stepService.listAvailable());
        return output;
    }

    public List<String> listScales() {
        return scaleService.listAvailable();
    }

    public List<String> listSteps() {
        return stepService.listAvailable();
    }

}
