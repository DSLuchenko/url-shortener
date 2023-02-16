package com.dsluchenko.app.url_shortener.controller;


import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortName}")
    public ResponseEntity<Void> redirectToShortName(@PathVariable String shortName) {
        var urlDto = urlService.getByShortName(shortName);
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(URI.create(urlDto.getTargetUrl()))
                .build();
    }

    @PostMapping(value = "/")
    public ResponseEntity<UrlDto> reduceTargetUrl(@RequestBody UrlDto data, HttpServletRequest request) {
        var urlDto = urlService.reduceTargetUrl(data.getTargetUrl());
        var uri = request.getRequestURL() + urlDto.getUri();
        urlDto.setUri(uri);
        return ResponseEntity.status(HttpStatus.CREATED).body(urlDto);
    }
}
