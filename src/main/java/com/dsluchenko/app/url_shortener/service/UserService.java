package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.UserDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.entity.User;

import java.util.List;

public interface UserService {
    User register(UserDto userDto);

    User findByLogin(String login);

}
