package com.dsluchenko.app.url_shortener.service.impl;

import com.dsluchenko.app.url_shortener.dto.request.url.UrlAuthorizedRequest;
import com.dsluchenko.app.url_shortener.dto.request.url.UrlUnauthorizedRequest;
import com.dsluchenko.app.url_shortener.dto.response.url.UrlResponse;
import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.exception.UrlNotFoundRuntimeException;
import com.dsluchenko.app.url_shortener.mapper.UrlMapper;
import com.dsluchenko.app.url_shortener.repository.UrlRepository;
import com.dsluchenko.app.url_shortener.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final UrlMapper mapper;

    public UrlServiceImpl(UrlRepository urlRepository, UrlMapper mapper) {
        this.urlRepository = urlRepository;
        this.mapper = mapper;
    }

    @Override
    public UrlResponse reduceTargetUrl(UrlAuthorizedRequest request) {
        Url url = mapper.mapToEntity(request);

        Optional<Url> byTargetUrlAndUserId = urlRepository
                .findByTargetUrlAndUserId(
                        url.getTargetUrl(),
                        url.getUser().getId());

        UrlResponse response = byTargetUrlAndUserId.isPresent()
                ? mapper.mapToDto(byTargetUrlAndUserId.get())
                : mapper.mapToDto(urlRepository.save(url));

        return response;
    }

    @Override
    public UrlResponse reduceTargetUrl(UrlUnauthorizedRequest request) {
        Url url = mapper.mapToEntity(request);
        UrlResponse response = mapper.mapToDto(urlRepository.save(url));

        return response;
    }


    @Override
    public String getTargetUrlByShortName(String shortName) {
        return urlRepository.findTargetUrlByShortName(shortName)
                .orElseThrow(() -> new UrlNotFoundRuntimeException(shortName));
    }

    @Override
    public List<UrlResponse> getUrlsByUserId(Long userId) {
        List<Url> urls = urlRepository.findByUserId(userId).orElse(new ArrayList<>());

        return mapper.listUrlToListUrlDto(urls);
    }
}
