package com.dsluchenko.app.url_shortener.controller;

import com.dsluchenko.app.url_shortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RedirectController {
    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortName}")
    public ResponseEntity<Void> redirectToShortName(@PathVariable String shortName) {
        var targetUrl = urlService.getTargetUrlByShortName(shortName);
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(URI.create(targetUrl))
                .build();
    }
}
