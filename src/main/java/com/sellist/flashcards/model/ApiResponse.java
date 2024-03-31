package com.sellist.flashcards.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private String status;
    private int httpStatus;
    private String message;
    private T data;
}
