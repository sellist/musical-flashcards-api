package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.FormInformation;
import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.service.InfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void testGetInfo() {
        FormInformation formInformation = new FormInformation();
        when(infoService.getInfo()).thenReturn(formInformation);

        ApiResponse<FormInformation> response = informationController.getInfo();

        assertEquals(formInformation, response.getData());
        assertEquals("OK", response.getMetadata().getStatus());
    }

}