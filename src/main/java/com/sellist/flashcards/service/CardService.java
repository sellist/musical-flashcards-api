package com.sellist.flashcards.service;

import com.sellist.flashcards.model.Card;
import com.sellist.flashcards.model.Note;
import com.sellist.flashcards.model.request.CardsRequest;
import com.sellist.flashcards.model.request.NotesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final AbcJsService abcJsService;
    private final ScaleService scaleService;
    private final NoteService noteService;

    @Autowired
    public CardService(AbcJsService abcJsService, ScaleService scaleService, NoteService noteService) {
        this.abcJsService = abcJsService;
        this.scaleService = scaleService;
        this.noteService = noteService;
    }

    public List<Card> generateCards(CardsRequest requests) {
        List<Card> output = new ArrayList<>();

        List<Note> s = scaleService.buildScale(
                requests.getScaleType(),
                requests.getStartingNote(),
                requests.getOctaves()
        );

        s.forEach(n -> output.add(generateCard(n)));

        return output;
    }

    public List<Card> generateCards(List<CardsRequest> requests) {
        List<Card> output = new ArrayList<>();
        for (CardsRequest request : requests) {
            output.addAll(generateCards(request));
        }
        return output;
    }

    public List<Card> generateCardsFromNotes(NotesRequest noteNames) {
        List<Card> output = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for (String noteName : noteNames.getNotes()) {
            try {
                output.add(generateCard((noteService.generateNote(noteName))));
            } catch (Exception e) {
                errors.add(noteName);
            }
        }
        if (!errors.isEmpty()) {
            System.out.println("Errors: " + errors);
        }
        return output;
    }

    public Card generateCard(Note note) {
        return new Card(abcJsService.generateAbcJs(note), note.toString());
    }
}
