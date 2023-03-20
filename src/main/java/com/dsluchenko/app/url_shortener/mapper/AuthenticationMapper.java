package com.dsluchenko.app.url_shortener.mapper;

import com.dsluchenko.app.url_shortener.dto.authentication.request.AuthenticationRequest;
import com.dsluchenko.app.url_shortener.dto.authentication.response.AuthenticationResponse;
import com.dsluchenko.app.url_shortener.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthenticationMapper {
    User mapToEntity(AuthenticationRequest request);
}
