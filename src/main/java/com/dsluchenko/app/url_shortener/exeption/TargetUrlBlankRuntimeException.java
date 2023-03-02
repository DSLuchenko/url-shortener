package com.dsluchenko.app.url_shortener.exeption;

public class TargetUrlBlankRuntimeException extends RuntimeException {

    private static final String message = "Target url won’t have to blank";

    public TargetUrlBlankRuntimeException() {
        super(message);
    }
}
