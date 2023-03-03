package com.dsluchenko.app.url_shortener.controller;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.service.UrlService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.net.URI;


@RestController
@RequestMapping(value = "/api/url")
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

    @PostMapping(value = "/reduceUrl")
    public ResponseEntity<UrlDto> reduceTargetUrl(@RequestBody @Valid UrlDto urlDto, HttpServletRequest request) {
        urlDto = urlService.reduceTargetUrl(urlDto);
        var indexUri = request.getRequestURL().indexOf(request.getRequestURI());
        var urlWithOutUri = request.getRequestURL().substring(0, indexUri);
        var newUrl = String.join("/", urlWithOutUri, urlDto.getUri());
        urlDto.setUri(newUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(urlDto);
    }
}
