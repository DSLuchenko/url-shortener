package com.dsluchenko.app.url_shortener.service.impl;

import com.dsluchenko.app.url_shortener.entity.User;
import com.dsluchenko.app.url_shortener.exception.authenticationException.UserNotFoundAuthenticationException;
import com.dsluchenko.app.url_shortener.repository.UserRepository;
import com.dsluchenko.app.url_shortener.service.UserService;

import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByLogin(String login) {

        return userRepository
                .findByLogin(login)
                .orElseThrow(() ->
                        new UserNotFoundAuthenticationException(login));
    }

}
