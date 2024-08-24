package com.sellist.flashcards.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private T data;

    private ResponseMetadata metadata;

}
