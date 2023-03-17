package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.UrlDto;

import java.util.List;

public interface UrlService {
    UrlDto reduceTargetUrl(UrlDto urlDto);

    String getTargetUrlByShortName(String shortName);

    List<UrlDto> getUrlsByUserId(Long userId);

}
