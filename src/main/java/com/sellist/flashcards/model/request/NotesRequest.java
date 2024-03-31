package com.sellist.flashcards.model.request;

import lombok.Data;

import java.util.List;

@Data
public class NotesRequest {
    List<String> notes;
}
