package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.Card;
import com.sellist.flashcards.model.request.CardsRequest;
import com.sellist.flashcards.model.response.ApiResponse;
import com.sellist.flashcards.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CardControllerTest {

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCards() {
        List<CardsRequest> request = Collections.singletonList(new CardsRequest());
        List<Card> cards = Collections.singletonList(new Card());
        when(cardService.generateCards(request)).thenReturn(cards);

        ApiResponse<List<Card>> response = cardController.getCards(request);

        assertEquals(cards, response.getData());
        assertEquals(HttpStatus.OK.value(), response.getMetadata().getCode());
    }

}