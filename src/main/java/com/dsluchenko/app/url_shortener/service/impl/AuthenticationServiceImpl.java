package com.dsluchenko.app.url_shortener.service.impl;

import com.dsluchenko.app.url_shortener.dto.authentication.response.AuthenticationResponse;
import com.dsluchenko.app.url_shortener.dto.authentication.request.AuthenticationRequest;
import com.dsluchenko.app.url_shortener.entity.Role;
import com.dsluchenko.app.url_shortener.entity.User;
import com.dsluchenko.app.url_shortener.exeption.authenticationException.UserAlreadyExistAuthenticationException;

import com.dsluchenko.app.url_shortener.mapper.AuthenticationMapper;
import com.dsluchenko.app.url_shortener.repository.RoleRepository;
import com.dsluchenko.app.url_shortener.repository.UserRepository;
import com.dsluchenko.app.url_shortener.security.jwt.JwtTokenProvider;
import com.dsluchenko.app.url_shortener.security.jwt.JwtUser;
import com.dsluchenko.app.url_shortener.service.AuthenticationService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationMapper authenticationMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, AuthenticationMapper authenticationMapper, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationMapper = authenticationMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        JwtUser jwtUser = (JwtUser) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())).getPrincipal();

        String token = jwtTokenProvider.createToken(jwtUser.getUsername(),
                                                    jwtUser.getAuthorities()
                                                            .stream()
                                                            .map(a -> new Role(a.getAuthority()))
                                                            .collect(Collectors.toSet()));

        return new AuthenticationResponse(jwtUser.getId(), jwtUser.getUsername(), token);
    }

    @Override
    public AuthenticationResponse register(AuthenticationRequest request) {
        if (userRepository.existsUserByLogin(request.getLogin())) {
            throw new UserAlreadyExistAuthenticationException(request.getLogin());
        }

        var roleUser = roleRepository.findByName("ROLE_USER")
                .orElseGet(
                        () -> roleRepository
                                .save(new Role("ROLE_USER")));

        User user = authenticationMapper.mapToEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleUser);
        User savedUser = userRepository.save(user);

        String token = jwtTokenProvider.createToken(savedUser.getLogin(), savedUser.getRoles());

        return new AuthenticationResponse(savedUser.getId(), savedUser.getLogin(), token);
    }
}
