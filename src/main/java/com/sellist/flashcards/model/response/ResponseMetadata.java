package com.sellist.flashcards.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMetadata {
    private String status;
    private int code;
    private String message;
}
