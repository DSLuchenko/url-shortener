package com.dsluchenko.app.url_shortener.mapper;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.dto.UserDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.entity.User;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UrlMapper {

    @Mapping(source = "shortName", target = "uri")
    @Mapping(source = "user", target = "userId", qualifiedByName = "idFromUser")
    UrlDto urlToUrlDto(Url url);

    @Mapping(source = "uri", target = "shortName")
    @Mapping(source = "userId", target = "user", qualifiedByName = "userById")
    Url urlDtoToUrl(UrlDto urlDto);

    @Named("userById")
    default User userById(Long id) {
        var user = new User();
        user.setId(id);
        return user;
    }

    @Named("idFromUser")
    default Long userById(User user) {
        return user.getId();
    }

}
