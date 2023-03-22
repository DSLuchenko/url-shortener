package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.request.url.UrlAuthorizedRequest;
import com.dsluchenko.app.url_shortener.dto.request.url.UrlUnauthorizedRequest;
import com.dsluchenko.app.url_shortener.dto.response.url.UrlResponse;

import java.util.List;

public interface UrlService {
    UrlResponse reduceTargetUrl(UrlAuthorizedRequest request);

    UrlResponse reduceTargetUrl(UrlUnauthorizedRequest request);

    String getTargetUrlByShortName(String shortName);

    List<UrlResponse> getUrlsByUserId(Long userId);

}
