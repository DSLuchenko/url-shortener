package com.dsluchenko.app.url_shortener.controller;

import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Url>> getUserUrlsById(@PathVariable Long userId) throws Exception {
        var urls = userService.getUserUrlsByUserId(userId);

        return ResponseEntity.ok(urls);
    }

}
