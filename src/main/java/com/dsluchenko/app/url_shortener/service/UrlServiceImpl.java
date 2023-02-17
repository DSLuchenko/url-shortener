package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.mapper.UrlMapper;
import com.dsluchenko.app.url_shortener.repository.UrlRepository;
import com.dsluchenko.app.url_shortener.exeption.TargetUrlBlankException;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public UrlDto reduceTargetUrl(String targetUrl) throws TargetUrlBlankException {
        targetUrl = prepareUrl(targetUrl);

        var sameTargetUrl = urlRepository.findByTargetUrl(targetUrl).orElse(null);
        if (sameTargetUrl != null) {
            return mapper.urlToUrlDto(sameTargetUrl);
        }

        var url = new Url(
                targetUrl,
                UUID.randomUUID().toString().substring(0, 7),
                new Date(),
                null);
        urlRepository.save(url);

        return mapper.urlToUrlDto(url);
    }

    @Override
    public UrlDto getByShortName(String shortName) {
        var url = urlRepository.findByShortName(shortName).orElseThrow();

        return mapper.urlToUrlDto(url);
    }

    private String prepareUrl(String url) throws TargetUrlBlankException {
        if (url.isBlank()) {
            throw new TargetUrlBlankException();
        }
        url = url.trim();

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }
}
