package com.dsluchenko.app.url_shortener.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "urls_dbt",
        indexes = {
                @Index(name = "urls_targetUrl_idx", columnList = "targetUrl"),
                @Index(name = "urls_shortName_idx", columnList = "shortName")
        })

public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(nullable = false)
    String targetUrl;
    @Column(length = 7, nullable = false)
    String shortName;
    @Column(nullable = false)
    Date createdAt;
    @Column(nullable = true)
    Date updatedAt;

    public Url() {
    }

    public Url(String targetUrl, String shortName, Date createdAt, Date updatedAt) {
        this.targetUrl = targetUrl;
        this.shortName = shortName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getShortName() {
        return shortName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
