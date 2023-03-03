package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.exeption.TargetUrlBlankRuntimeException;

public interface UrlService {
    UrlDto reduceTargetUrl(UrlDto urlDto);

    UrlDto getByShortName(String shortName);

}
