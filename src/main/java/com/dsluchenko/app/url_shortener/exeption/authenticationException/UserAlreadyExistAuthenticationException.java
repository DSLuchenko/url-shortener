package com.dsluchenko.app.url_shortener.exeption.authenticationException;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    public UserAlreadyExistAuthenticationException(final String userName) {
        super(String.format("User %s is already exist", userName));
    }

}
