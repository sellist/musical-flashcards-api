package com.sellist.flashcards.controller;

import com.sellist.flashcards.model.response.ResponseMetadata;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    public ResponseMetadata generateMetadata() {
        return new ResponseMetadata("OK", 200, "Success");
    }

    @ExceptionHandler(Exception.class)
    public ResponseMetadata handleException(Exception e) {
        return new ResponseMetadata("ERROR", 500, e.getMessage());
    }
}
