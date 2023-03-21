package com.dsluchenko.app.url_shortener.dto.authentication.request;

import jakarta.validation.constraints.NotBlank;


public record RegisterRequest(@NotBlank String login, @NotBlank String password) {

}
