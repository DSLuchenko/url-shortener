package com.dsluchenko.app.url_shortener.repository;

import com.dsluchenko.app.url_shortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UrlRepository extends JpaRepository<Url, UUID> {
    Optional<Url> findByShortName(String shortName);

    Optional<Url> findByTargetUrlAndUserId(String targetUrl, Long userId);

    Optional<Url> findByTargetUrl(String targetUrl);

    Optional<List<Url>> findByUserId(Long userId);

    @Query("SELECT u.targetUrl FROM Url u WHERE u.shortName = :shortName")
    Optional<String> findTargetUrlByShortName(String shortName);
}
