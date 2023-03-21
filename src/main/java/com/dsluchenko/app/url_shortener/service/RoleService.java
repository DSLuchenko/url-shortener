package com.dsluchenko.app.url_shortener.service;

import com.dsluchenko.app.url_shortener.entity.Role;
import com.dsluchenko.app.url_shortener.service.impl.RoleServiceImpl;

public interface RoleService {
    Role getRoleByName(RoleServiceImpl.RoleName roleName);
}
