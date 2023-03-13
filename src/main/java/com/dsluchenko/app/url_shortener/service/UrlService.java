package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.exeption.TargetUrlBlankRuntimeException;

import java.util.List;

public interface UrlService {
    UrlDto reduceTargetUrl(UrlDto urlDto);
    UrlDto getByShortName(String shortName);

    List<UrlDto> getUrlsByUserId(Long userId);

}
