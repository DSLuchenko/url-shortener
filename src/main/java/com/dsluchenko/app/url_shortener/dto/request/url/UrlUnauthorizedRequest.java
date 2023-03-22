package com.dsluchenko.app.url_shortener.dto.request.url;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record UrlUnauthorizedRequest(
        @NotBlank
        @URL
        String targetUrl) {
}
