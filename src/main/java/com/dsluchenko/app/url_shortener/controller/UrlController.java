package com.dsluchenko.app.url_shortener.controller;

import com.dsluchenko.app.url_shortener.dto.request.url.UrlUnauthorizedRequest;
import com.dsluchenko.app.url_shortener.dto.response.url.UrlResponse;
import com.dsluchenko.app.url_shortener.service.UrlService;

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
    public ResponseEntity<UrlResponse> reduceTargetUrl(@RequestBody @Valid UrlUnauthorizedRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(urlService.reduceTargetUrl(request));
    }
}
