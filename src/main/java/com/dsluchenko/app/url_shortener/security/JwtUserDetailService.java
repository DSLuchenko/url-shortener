package com.dsluchenko.app.url_shortener.security;

import com.dsluchenko.app.url_shortener.entity.User;
import com.dsluchenko.app.url_shortener.exception.authenticationException.UserNotFoundAuthenticationException;
import com.dsluchenko.app.url_shortener.security.jwt.JwtUserFactory;
import com.dsluchenko.app.url_shortener.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByLogin(username);
            return JwtUserFactory.create(user);
        } catch (UserNotFoundAuthenticationException ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }
    }
}
