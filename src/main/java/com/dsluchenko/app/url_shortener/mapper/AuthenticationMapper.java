package com.dsluchenko.app.url_shortener.mapper;

import com.dsluchenko.app.url_shortener.dto.request.auth.AuthenticationRequest;
import com.dsluchenko.app.url_shortener.dto.request.auth.RegisterRequest;
import com.dsluchenko.app.url_shortener.entity.User;
import org.mapstruct.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthenticationMapper {
    @AfterMapping
    default void encodePassword(@MappingTarget User user, @Context BCryptPasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }


    @Mapping(target = "urls", expression = "java(new java.util.HashSet<>())")
    @Mapping(target = "roles", expression = "java(new java.util.HashSet<>())")
    User mapToEntity(AuthenticationRequest request, @Context BCryptPasswordEncoder passwordEncoder);

    @Mapping(target = "urls", expression = "java(new java.util.HashSet<>())")
    @Mapping(target = "roles", expression = "java(new java.util.HashSet<>())")
    User mapToEntity(RegisterRequest request, @Context BCryptPasswordEncoder passwordEncoder);


}
