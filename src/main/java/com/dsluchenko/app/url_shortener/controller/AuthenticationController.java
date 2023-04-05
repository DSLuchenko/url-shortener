package com.dsluchenko.app.url_shortener.controller;


import com.dsluchenko.app.url_shortener.dto.request.auth.AuthenticationRequest;
import com.dsluchenko.app.url_shortener.dto.request.auth.RegisterRequest;
import com.dsluchenko.app.url_shortener.dto.response.auth.AuthenticationResponse;
import com.dsluchenko.app.url_shortener.service.AuthenticationService;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping( "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {

        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
