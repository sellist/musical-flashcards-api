package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.NotesNameRequest;
import com.sellist.flashcards.service.NoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/note")
public class NoteController extends BaseController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(path = "/notes", consumes = "application/json", produces = "application/json")
    public ApiResponse<List<Note>> getNotes(@RequestBody NotesNameRequest notes) {
        List<Note> notesList = new ArrayList<>();
        for (String note : notes.getNotes()) {
            notesList.add(noteService.generateNote(note));
        }
        return ApiResponse.<List<Note>>builder()
                .data(notesList)
                .metadata(generateMetadata())
                .build();
    }
}
