package com.dsluchenko.app.url_shortener.controller;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.security.jwt.JwtUser;
import com.dsluchenko.app.url_shortener.service.UrlService;
import com.dsluchenko.app.url_shortener.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UrlService urlService;

    public UserController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/getUrls")
    public ResponseEntity<List<UrlDto>> getUserUrls(Authentication authentication) {
        JwtUser user = (JwtUser) authentication.getPrincipal();
        List<UrlDto> urls = urlService.getUrlsByUserId(user.getId());
        return ResponseEntity.ok(urls);
    }

}
