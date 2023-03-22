package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.request.auth.RegisterRequest;
import com.dsluchenko.app.url_shortener.dto.response.auth.AuthenticationResponse;
import com.dsluchenko.app.url_shortener.dto.request.auth.AuthenticationRequest;
import com.dsluchenko.app.url_shortener.exception.authenticationException.UserAlreadyExistAuthenticationException;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(RegisterRequest request) throws UserAlreadyExistAuthenticationException;
}
