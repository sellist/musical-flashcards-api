package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.ApiResponse;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.NotesNameRequest;
import com.sellist.flashcards.service.NoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(path = "/notes/names", consumes = "application/json", produces = "application/json")
    public ApiResponse<List<Note>> getNotes(@RequestBody NotesNameRequest notes) {
        List<Note> notesList = new ArrayList<>();
        for (String note : notes.getNotes()) {
            notesList.add(noteService.generateNote(note));
        }
        return ApiResponse.<List<Note>>builder()
                .status("success")
                .httpStatus(200)
                .message("Notes generated successfully")
                .data(notesList)
                .build();
    }
}