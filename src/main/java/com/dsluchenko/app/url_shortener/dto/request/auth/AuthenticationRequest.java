package com.dsluchenko.app.url_shortener.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest
        (@NotBlank
         String login,
         @NotBlank
         String password) {
}
