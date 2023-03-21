package com.dsluchenko.app.url_shortener.dto.authentication.response;

public record AuthenticationResponse(Long userId, String login, String token) {
}
