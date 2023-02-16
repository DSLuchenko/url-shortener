package com.dsluchenko.app.url_shortener.service.exeption;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<?> handleNotFound(RuntimeException ex, WebRequest request) {

        //log
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .allow(HttpMethod.GET)
                .contentType(MediaType.APPLICATION_JSON)
                .build();

    }

    @ExceptionHandler(value = TargetUrlBlankException.class)
    protected ResponseEntity<?> TargetUrlBlankException(RuntimeException ex, WebRequest request) {
        //log
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .allow(HttpMethod.GET)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }

}
