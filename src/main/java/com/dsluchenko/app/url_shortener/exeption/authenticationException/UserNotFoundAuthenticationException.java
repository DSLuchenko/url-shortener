package com.dsluchenko.app.url_shortener.exeption.authenticationException;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundAuthenticationException extends AuthenticationException {
    public UserNotFoundAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserNotFoundAuthenticationException(String userName) {
        super(String.format("User %s not founded", userName));
    }
}
