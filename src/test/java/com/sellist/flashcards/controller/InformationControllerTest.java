package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.FormInformation;
import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.service.InfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class InformationControllerTest {
    @Mock
    private InfoService infoService;

    @InjectMocks
    private InformationController informationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFormInfo() {
        FormInformation formInformation = new FormInformation();
        when(infoService.getInfo()).thenReturn(formInformation);

        ApiResponse<FormInformation> response = informationController.getFormInfo();

        assertEquals(formInformation, response.getData());
        assertEquals("OK", response.getMetadata().getStatus());
    }

    @Test
    void testListScales() {
        List<String> scales = List.of("Scale1", "Scale2");
        when(infoService.listScales()).thenReturn(scales);

        ApiResponse<List<String>> response = informationController.listScales();

        assertEquals(scales, response.getData());
        assertEquals("OK", response.getMetadata().getStatus());
    }

    @Test
    void testListSteps() {
        List<String> steps = List.of("Step1", "Step2");
        when(infoService.listSteps()).thenReturn(steps);

        ApiResponse<List<String>> response = informationController.listSteps();

        assertEquals(steps, response.getData());
        assertEquals("OK", response.getMetadata().getStatus());
    }
}