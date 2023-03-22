package com.dsluchenko.app.url_shortener.controller;

import com.dsluchenko.app.url_shortener.dto.request.url.UrlAuthorizedRequest;
import com.dsluchenko.app.url_shortener.dto.response.url.UrlResponse;
import com.dsluchenko.app.url_shortener.security.jwt.JwtUser;
import com.dsluchenko.app.url_shortener.service.UrlService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UrlService urlService;

    public UserController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/getUrls")
    public ResponseEntity<List<UrlResponse>> getUserUrls(Authentication authentication) {
        JwtUser user = (JwtUser) authentication.getPrincipal();
        List<UrlResponse> urls = urlService.getUrlsByUserId(user.getId());
        return ResponseEntity.ok(urls);
    }

    @PostMapping("/reduceTargetUrl")
    public ResponseEntity<UrlResponse> reduceTargetUrl(@RequestBody @Valid UrlAuthorizedRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(urlService.reduceTargetUrl(request));
    }

}
