package com.sellist.flashcards.model;

import java.io.Serializable;
import java.util.Date;

public class ApiResponse implements Serializable {
    private Date timestamp;

    public ApiResponse() {
        this.timestamp = new Date();
    }

}
