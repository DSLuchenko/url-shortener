package com.dsluchenko.app.url_shortener.dto.authentication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest(@NotBlank
                                    @Size(min = 5, max = 15)
                                    String login,
                                    @NotBlank
                                    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
                                            message = "Minimum eight characters, at least one uppercase letter," +
                                                    " one lowercase letter and one number")
                                    String password) {
}
