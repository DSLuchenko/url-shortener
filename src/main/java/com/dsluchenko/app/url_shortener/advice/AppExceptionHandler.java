package com.dsluchenko.app.url_shortener.advice;

import com.dsluchenko.app.url_shortener.exeption.TargetUrlBlankRuntimeException;
import com.dsluchenko.app.url_shortener.exeption.UrlNotFoundRuntimeException;
import com.dsluchenko.app.url_shortener.exeption.authenticationException.UserAlreadyExistAuthenticationException;
import com.dsluchenko.app.url_shortener.exeption.authenticationException.UserNotFoundAuthenticationException;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NoSuchElementException.class, UrlNotFoundRuntimeException.class})
    public Map<String, String> handleNotFound(RuntimeException ex) {
        //log
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ex.getClass().toString(), ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = TargetUrlBlankRuntimeException.class)
    public Map<String, String> handleBadRequest(TargetUrlBlankRuntimeException ex) {
        //log
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ex.getClass().toString(), ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({UserAlreadyExistAuthenticationException.class, UserNotFoundAuthenticationException.class})
    public Map<String, String> handleConflict(AuthenticationException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ex.getClass().toString(), ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidResponseField(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
}
