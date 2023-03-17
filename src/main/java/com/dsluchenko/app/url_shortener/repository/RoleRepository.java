package com.dsluchenko.app.url_shortener.repository;

import com.dsluchenko.app.url_shortener.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String Name);
}
