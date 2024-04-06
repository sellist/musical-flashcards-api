package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.ApiResponse;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.NotesRequest;
import com.sellist.flashcards.service.NoteService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(path = "/notes", consumes = "application/json", produces = "application/json")
    public ApiResponse<List<Note>> getNotes(@RequestBody NotesRequest notes) {
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
