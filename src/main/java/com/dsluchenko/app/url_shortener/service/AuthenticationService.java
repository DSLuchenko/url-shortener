package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.authentication.response.AuthenticationResponse;
import com.dsluchenko.app.url_shortener.dto.authentication.request.AuthenticationRequest;
import com.dsluchenko.app.url_shortener.exception.authenticationException.UserAlreadyExistAuthenticationException;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(AuthenticationRequest request) throws UserAlreadyExistAuthenticationException;
}
