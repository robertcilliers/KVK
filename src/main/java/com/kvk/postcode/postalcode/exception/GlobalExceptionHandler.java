package com.kvk.postcode.postalcode.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PostalCodeNotFoundException.class)
    public String handlePostalCodeNotFoundException(PostalCodeNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(AddPostalCodeException.class)
    public String handleAddPostalCodeException(AddPostalCodeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(HttpRequestException.class)
    public String handleHttpRequestException(HttpRequestException exception) {
        return exception.getMessage();
    }
}

