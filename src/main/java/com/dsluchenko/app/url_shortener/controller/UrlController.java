package com.dsluchenko.app.url_shortener.controller;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/url")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/reduceUrl")
    public ResponseEntity<UrlDto> reduceTargetUrl(@RequestBody @Valid UrlDto urlDto, HttpServletRequest request) {
        var responseUrlDto = urlService.reduceTargetUrl(urlDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUrlDto);
    }
}
