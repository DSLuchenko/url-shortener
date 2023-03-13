package com.dsluchenko.app.url_shortener.mapper;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import com.dsluchenko.app.url_shortener.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UrlMapper {

    @Mapping(source = "shortName", target = "uri")
    @Mapping(source = "user", target = "userId", qualifiedByName = "idFromUser")
    UrlDto urlToUrlDto(Url url);

    @Mapping(source = "uri", target = "shortName")
    @Mapping(source = "userId", target = "user", qualifiedByName = "userById")
    Url urlDtoToUrl(UrlDto urlDto);

    List<UrlDto> listUrlToListUrlDto(List<Url> urls);

    @Named("userById")
    default User userById(Long id) {
        var user = new User();
        user.setId(id);
        return user;
    }

    @Named("idFromUser")
    default Long idFromUser(User user) {
        return user.getId();
    }

}
