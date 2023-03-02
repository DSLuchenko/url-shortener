package com.dsluchenko.app.url_shortener.controller;

import com.dsluchenko.app.url_shortener.dto.UserDto;
import com.dsluchenko.app.url_shortener.entity.User;
import com.dsluchenko.app.url_shortener.security.jwt.JwtTokenProvider;
import com.dsluchenko.app.url_shortener.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserDto requestDto) {

        String login = requestDto.getLogin();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, requestDto.getPassword()));

        User user = userService.findByLogin(login);

        String token = jwtTokenProvider.createToken(login, user.getRoles());

        Map<Object, Object> response = new HashMap<>();

        response.put("login", login);
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDto requestDto){
        var user = userService.register(requestDto);
        String token = jwtTokenProvider.createToken(user.getLogin(), user.getRoles());

        Map<String, String> response = new HashMap<>();

        response.put("login", user.getLogin());
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
