package com.dsluchenko.app.url_shortener.dto.request.url;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record UrlAuthorizedRequest(
        @NotBlank
        @URL
        String targetUrl,
        @Size(min = 7, max = 10)
        String shortName) {
}
