package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.exeption.TargetUrlBlankRuntimeException;

public interface UrlService {
    UrlDto reduceTargetUrl(String targetUrl) throws TargetUrlBlankRuntimeException;

    UrlDto getByShortName(String shortName);
}
