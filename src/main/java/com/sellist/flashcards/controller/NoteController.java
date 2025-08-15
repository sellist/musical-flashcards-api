package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.service.NoteService;
import com.sellist.flashcards.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/notes")
    public ApiResponse<List<Note>> getNotes(@RequestParam String notes) {
        List<String> noteNames = ControllerUtils.deserialize(notes);
        List<Note> notesList = new ArrayList<>();
        for (String note : noteNames) {
            try {
                notesList.add(noteService.generateNote(note));
            } catch (Exception e) {
                log.warn("Invalid note input: {} - {}", note, e.getMessage());
            }
        }
        return ApiResponse.<List<Note>>builder()
                .data(notesList)
                .metadata(generateMetadata())
                .build();
    }
}
