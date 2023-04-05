package com.dsluchenko.app.url_shortener.advice;

import com.dsluchenko.app.url_shortener.dto.response.error.ErrorResponse;
import com.dsluchenko.app.url_shortener.exception.UrlNotFoundRuntimeException;
import com.dsluchenko.app.url_shortener.exception.authenticationException.UnathorizedException;
import com.dsluchenko.app.url_shortener.exception.authenticationException.UserAlreadyExistAuthenticationException;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.*;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NoSuchElementException.class, UrlNotFoundRuntimeException.class})
    public ErrorResponse handleNotFound(RuntimeException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({UserAlreadyExistAuthenticationException.class})
    public ErrorResponse handleConflict(AuthenticationException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnathorizedException.class})
    public ErrorResponse handleUnauthorized(AuthenticationException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Set<ErrorResponse> handleInvalidResponseField(MethodArgumentNotValidException ex) {
        Set<ErrorResponse> errorSet = new HashSet<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errorSet.add(new ErrorResponse(
                                error.getField()
                                        .concat(" : ")
                                        .concat(error.getDefaultMessage()))));
        return errorSet;
    }

}
