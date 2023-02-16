package com.dsluchenko.app.url_shortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
public class Url {

    @Id
    @GeneratedValue
    UUID id;

    String targetUrl;

    String shortName;

    Date createdAt;

    Date updatedAt;

    public Url() {
    }

    public Url(String targetUrl, String shortName, Date createdAt, Date updatedAt) {
        this.targetUrl = targetUrl;
        this.shortName = shortName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
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

    public void setId(UUID id) {
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
