package com.dsluchenko.app.url_shortener.exception;

public class UrlNotFoundRuntimeException extends RuntimeException {
    public UrlNotFoundRuntimeException(String message) {
        super(String.format("%s not found", message));
    }
}
