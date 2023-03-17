package com.dsluchenko.app.url_shortener.dto.authentication.response;

public class AuthenticationResponse {
    private Long id;
    private String login;
    private String token;

    public AuthenticationResponse(Long id, String login, String token) {
        this.id = id;
        this.login = login;
        this.token = token;
    }
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

}
