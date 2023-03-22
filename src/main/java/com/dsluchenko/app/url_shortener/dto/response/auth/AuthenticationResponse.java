package com.dsluchenko.app.url_shortener.dto.response.auth;

public record AuthenticationResponse(Long userId, String login, String token) {
}
