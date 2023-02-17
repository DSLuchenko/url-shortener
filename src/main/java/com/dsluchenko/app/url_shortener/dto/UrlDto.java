package com.dsluchenko.app.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class UrlDto {
    @NotBlank
    @URL
    String targetUrl;
    String uri;

    public UrlDto() {
    }

    public UrlDto(String targetUrl, String shortName) {
        this.targetUrl = targetUrl;
        this.uri = shortName;
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
}
