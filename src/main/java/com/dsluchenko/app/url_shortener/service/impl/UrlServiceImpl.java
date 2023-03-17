package com.dsluchenko.app.url_shortener.service.impl;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.exeption.UrlNotFoundRuntimeException;
import com.dsluchenko.app.url_shortener.mapper.UrlMapper;
import com.dsluchenko.app.url_shortener.repository.UrlRepository;
import com.dsluchenko.app.url_shortener.exeption.TargetUrlBlankRuntimeException;
import com.dsluchenko.app.url_shortener.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final UrlMapper mapper;

    public UrlServiceImpl(UrlRepository urlRepository, UrlMapper mapper) {
        this.urlRepository = urlRepository;
        this.mapper = mapper;
    }

    @Override
    public UrlDto reduceTargetUrl(UrlDto urlDto) throws TargetUrlBlankRuntimeException {
        var targetUrl = prepareUrl(urlDto.getTargetUrl());
        if (urlDto.getUserId() != 1L) {
            Optional<Url> byTargetUrlAndUserId = urlRepository.findByTargetUrlAndUserId(targetUrl, urlDto.getUserId());
            if (byTargetUrlAndUserId.isPresent()) {
                return mapper.urlToUrlDto(byTargetUrlAndUserId.get());
            }
        }
        Url url = mapper.urlDtoToUrl(urlDto);
        if (url.getShortName() == null) {
            url.setShortName(UUID.randomUUID().toString().substring(0, 7));
        }
        url.setCreatedAt(new Date());
        url.setUpdatedAt(new Date());

        url = urlRepository.save(url);

        return mapper.urlToUrlDto(url);
    }

    @Override
    public String getTargetUrlByShortName(String shortName) {

        return urlRepository.findTargetUrlByShortName(shortName)
                .orElseThrow(() -> new UrlNotFoundRuntimeException(shortName));
    }

    @Override
    public List<UrlDto> getUrlsByUserId(Long userId) {
        List<Url> urls = urlRepository.findByUserId(userId).orElseThrow();

        return mapper.listUrlToListUrlDto(urls);
    }

    private String prepareUrl(String url) {
        if (url.isBlank()) {
            throw new TargetUrlBlankRuntimeException();
        }
        url = url.trim();

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }
}
