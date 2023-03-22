package com.dsluchenko.app.url_shortener.dto.response.url;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record UrlResponse(
        @NotBlank
        @URL
        String targetUrl,
        String shortName) {

}

