package com.dsluchenko.app.url_shortener.service.impl;

import com.dsluchenko.app.url_shortener.dto.authentication.response.AuthenticationResponse;
import com.dsluchenko.app.url_shortener.dto.authentication.request.AuthenticationRequest;
import com.dsluchenko.app.url_shortener.entity.Role;
import com.dsluchenko.app.url_shortener.entity.User;
import com.dsluchenko.app.url_shortener.exception.authenticationException.UnathorizedException;
import com.dsluchenko.app.url_shortener.exception.authenticationException.UserAlreadyExistAuthenticationException;

import com.dsluchenko.app.url_shortener.mapper.AuthenticationMapper;
import com.dsluchenko.app.url_shortener.repository.RoleRepository;
import com.dsluchenko.app.url_shortener.repository.UserRepository;
import com.dsluchenko.app.url_shortener.security.jwt.JwtTokenProvider;
import com.dsluchenko.app.url_shortener.security.jwt.JwtUser;
import com.dsluchenko.app.url_shortener.service.AuthenticationService;

import com.dsluchenko.app.url_shortener.service.RoleService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
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
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, AuthenticationMapper authenticationMapper, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationMapper = authenticationMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            JwtUser jwtUser = (JwtUser) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            request.login(),
                            request.password()))
                    .getPrincipal();

            String token = jwtTokenProvider.createToken(jwtUser.getUsername(),
                    jwtUser.getAuthorities()
                            .stream()
                            .map(a -> new Role(a.getAuthority()))
                            .collect(Collectors.toSet()));

            return new AuthenticationResponse(jwtUser.getId(), jwtUser.getUsername(), token);
        } catch (AuthenticationException e) {
            throw new UnathorizedException();
        }
    }

    @Override
    public AuthenticationResponse register(AuthenticationRequest request) {
        try {
            var roleUser = roleService.getRoleByName(RoleServiceImpl.RoleName.ROLE_USER);

            User user = authenticationMapper.mapToEntity(request, passwordEncoder);
            user.getRoles().add(roleUser);

            User savedUser = userRepository.save(user);

            String token = jwtTokenProvider.createToken(savedUser.getLogin(), savedUser.getRoles());

            return new AuthenticationResponse(savedUser.getId(), savedUser.getLogin(), token);
        } catch (DataIntegrityViolationException ex) {
            //System.out.println(ex.getRootCause().getMessage()); get logger!
            throw new UserAlreadyExistAuthenticationException(request.login());
        }
    }
}
