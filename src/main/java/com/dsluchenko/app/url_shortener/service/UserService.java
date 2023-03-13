package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.UserDto;
import com.dsluchenko.app.url_shortener.entity.User;

public interface UserService {
    User register(UserDto userDto);

    User findByLogin(String login);

}
