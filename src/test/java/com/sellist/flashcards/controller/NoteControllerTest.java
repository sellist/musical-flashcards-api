package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.NotesRequest;
import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class NoteControllerTest {
    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNotes() {
        NotesRequest request = new NotesRequest();
        request.setNotes(List.of("C", "D", "E"));
        Note noteC = new Note();
        Note noteD = new Note();
        Note noteE = new Note();
        when(noteService.generateNote("C")).thenReturn(noteC);
        when(noteService.generateNote("D")).thenReturn(noteD);
        when(noteService.generateNote("E")).thenReturn(noteE);

        ApiResponse<List<Note>> response = noteController.getNotes(request);

        assertEquals(List.of(noteC, noteD, noteE), response.getData());
        assertEquals("OK", response.getMetadata().getStatus());
    }
}