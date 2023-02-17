package com.dsluchenko.app.url_shortener.exeption;

public class TargetUrlBlankException extends Exception{

    private static final String message = "Target url won’t have to blank";
    public TargetUrlBlankException(){
        super(message);
    }
}
