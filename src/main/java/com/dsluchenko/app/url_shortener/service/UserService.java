package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.entity.User;

public interface UserService {
    User findByLogin(String login);

}
