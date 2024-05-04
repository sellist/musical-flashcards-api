package com.sellist.flashcards.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private String status;
    private int code;
    private String message;
    private T data;
}
