package com.dsluchenko.app.url_shortener.service.impl;

import com.dsluchenko.app.url_shortener.entity.Role;
import com.dsluchenko.app.url_shortener.repository.RoleRepository;
import com.dsluchenko.app.url_shortener.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(RoleName roleName) {
        String name = roleName.toString();
        return roleRepository.findByName(name)
                .orElseGet(
                        () -> roleRepository
                                .save(new Role(name)));
    }

    public enum RoleName {
        ROLE_USER,
        ROLE_ADMIN
    }
}
