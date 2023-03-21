package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.entity.User;
import com.dsluchenko.app.url_shortener.exception.authenticationException.UserNotFoundAuthenticationException;

public interface UserService {
    User findByLogin(String login) throws UserNotFoundAuthenticationException;

}
