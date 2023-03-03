package com.dsluchenko.app.url_shortener.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "urls_dbt", indexes = {@Index(name = "urls_targetUrl_idx", columnList = "targetUrl"),
        @Index(name = "urls_shortName_idx", columnList = "shortName")})

public class Url {
    @Id
    @GeneratedValue(generator = "urls_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "urls_id_seq", sequenceName = "urls_id_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String targetUrl;
    @Column(nullable = false)
    private String shortName;
    @Column(nullable = false)
    private Date createdAt;
    @Column(nullable = true)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
