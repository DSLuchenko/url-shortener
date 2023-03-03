package com.dsluchenko.app.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class UrlDto {
    @NotBlank
    @URL
    private String targetUrl;
    private String uri;
    private Long userId = 1L;//unauthorized_user

    public UrlDto() {
    }

    public UrlDto(String targetUrl, String shortName, Long userId) {
        this.targetUrl = targetUrl;
        this.uri = shortName;
        if (userId != null) {
            this.userId = userId;
        }
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

