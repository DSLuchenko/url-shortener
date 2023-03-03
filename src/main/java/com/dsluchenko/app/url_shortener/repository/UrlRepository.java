package com.dsluchenko.app.url_shortener.repository;

import com.dsluchenko.app.url_shortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlRepository extends JpaRepository<Url, UUID> {
    Optional<Url> findByShortName(String shortName);

    Optional<Url> findByTargetUrlAndUserId(String targetUrl, Long userId);

    Optional<Url> findByTargetUrl(String targetUrl);
}
