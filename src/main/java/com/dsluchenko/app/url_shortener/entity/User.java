package com.dsluchenko.app.url_shortener.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users_dbt",
        indexes = {@Index(name = "users_login_idx", columnList = "login")
        })
public class User {
    @Id
    @GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_urls_dbt",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "url_id", referencedColumnName = "id")
    )
    private List<Url> urls;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles_dbt",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    public User() {
    }

    public User(String login, String password, List<Url> urls, List<Role> roles) {
        this.login = login;
        this.password = password;
        this.urls = urls;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
