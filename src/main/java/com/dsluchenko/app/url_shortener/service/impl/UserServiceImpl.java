package com.dsluchenko.app.url_shortener.service.impl;

import com.dsluchenko.app.url_shortener.dto.UserDto;
import com.dsluchenko.app.url_shortener.entity.Role;
import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.entity.User;
import com.dsluchenko.app.url_shortener.exeption.authenticationException.UserAlreadyExistAuthenticationException;
import com.dsluchenko.app.url_shortener.exeption.authenticationException.UserNotFoundAuthenticationException;
import com.dsluchenko.app.url_shortener.mapper.UserMapper;
import com.dsluchenko.app.url_shortener.repository.RoleRepository;
import com.dsluchenko.app.url_shortener.repository.UserRepository;
import com.dsluchenko.app.url_shortener.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public User register(UserDto userDto) {
        if (userRepository.existsUserByLogin(userDto.getLogin())) {
            throw new UserAlreadyExistAuthenticationException(userDto.getLogin());
        }
        List<Role> userRoles = new ArrayList<>();
        var roleUser = roleRepository.findByName("ROLE_USER");
        userRoles.add(roleUser);

        var user = userMapper.userFromUserDto(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setUrls(new ArrayList<Url>());

        var registeredUser = userRepository.save(user);
        return registeredUser;
    }

    @Override
    public User findByLogin(String login) {
        var foundUser = userRepository.findByLogin(login).orElse(null);
        if (foundUser == null) {
            throw new UserNotFoundAuthenticationException(login);
        }
        return foundUser;
    }

}
