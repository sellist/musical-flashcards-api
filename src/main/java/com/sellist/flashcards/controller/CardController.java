package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.model.Card;
import com.sellist.flashcards.model.request.CardsRequest;
import com.sellist.flashcards.model.request.NotesNameRequest;
import com.sellist.flashcards.service.CardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping("/card")
public class CardController extends BaseController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping("/from-notes")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<Card>> getCard(@RequestBody NotesNameRequest notes) {
        return ApiResponse.<List<Card>>builder()
                .data(cardService.generateCards(notes))
                .metadata(generateMetadata())
                .build();
    }

    @PostMapping("/generate")
    public ApiResponse<List<Card>> generateCards(@RequestBody CardsRequest notes) {
        return ApiResponse.<List<Card>>builder()
                .data(cardService.generateCards(notes))
                .metadata(generateMetadata())
                .build();
    }

}
