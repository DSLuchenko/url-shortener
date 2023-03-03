package com.dsluchenko.app.url_shortener.config;

import com.dsluchenko.app.url_shortener.security.jwt.JwtConfigurer;
import com.dsluchenko.app.url_shortener.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String USER_ENDPOINT = "/api/user/**";
    private static final String URL_ENDPOINT = "/api/url/*";
    private static final String LOGIN_ENDPOINT = "/api/auth/*";

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((authorize) -> {
                    authorize
                            .requestMatchers(LOGIN_ENDPOINT).permitAll()
                            .requestMatchers(URL_ENDPOINT).permitAll()
                            .requestMatchers(USER_ENDPOINT).hasAnyRole("USER", "ADMIN")
                            .requestMatchers(ADMIN_ENDPOINT).hasAnyRole("ADMIN")
                            .anyRequest().authenticated();
                }).apply(new JwtConfigurer(jwtTokenProvider));

        return http.build();

    }

}
