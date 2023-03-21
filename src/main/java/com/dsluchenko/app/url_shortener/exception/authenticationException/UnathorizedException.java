package com.dsluchenko.app.url_shortener.exception.authenticationException;

import org.springframework.security.core.AuthenticationException;

public class UnathorizedException extends AuthenticationException {
    public UnathorizedException() {
        super("Username or password is wrong");
    }
}
