package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.service.exeption.TargetUrlBlankException;

public interface UrlService {
    UrlDto reduceTargetUrl(String targetUrl) throws TargetUrlBlankException;

    UrlDto getByShortName(String shortName);
}
