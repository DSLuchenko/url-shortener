package com.dsluchenko.app.url_shortener.repository;

import com.dsluchenko.app.url_shortener.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
