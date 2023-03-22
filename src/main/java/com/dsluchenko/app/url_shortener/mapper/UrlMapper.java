package com.dsluchenko.app.url_shortener.mapper;

import com.dsluchenko.app.url_shortener.dto.request.url.UrlAuthorizedRequest;
import com.dsluchenko.app.url_shortener.dto.request.url.UrlUnauthorizedRequest;
import com.dsluchenko.app.url_shortener.dto.response.url.UrlResponse;
import com.dsluchenko.app.url_shortener.entity.Url;

import com.dsluchenko.app.url_shortener.entity.User;
import com.dsluchenko.app.url_shortener.repository.UrlRepository;
import com.dsluchenko.app.url_shortener.security.jwt.JwtUser;
import com.dsluchenko.app.url_shortener.service.UserService;
import org.mapstruct.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UrlMapper {
    UrlResponse mapToDto(Url url);

    @AfterMapping
    default void buildUrl(@MappingTarget Url url, UrlAuthorizedRequest request) {

        JwtUser jwtUser = (JwtUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = new User();
        user.setId(jwtUser.getId());

        url.setUser(user);
        if (url.getShortName() == null) {
            url.setShortName(UUID
                    .randomUUID()
                    .toString()
                    .substring(0, 7));
        }

        if (url.getTargetUrl().endsWith("/")) {
            url.setTargetUrl(url.getTargetUrl()
                    .substring(0, url
                            .getTargetUrl()
                            .length() - 1));
        }

        url.setCreatedAt(new Date());
        url.setUpdatedAt(new Date());
    }


    Url mapToEntity(UrlAuthorizedRequest request);

    @AfterMapping
    default void buildUrl(@MappingTarget Url url, UrlUnauthorizedRequest request) {
        User user = new User();
        user.setId(1L);//UNAUTHORIZED USER

        url.setUser(user);

        if (url.getTargetUrl().endsWith("/")) {
            url.setTargetUrl(url.getTargetUrl()
                    .substring(0, url
                            .getTargetUrl()
                            .length() - 1));
        }

        url.setShortName(UUID
                .randomUUID()
                .toString()
                .substring(0, 7));

        url.setCreatedAt(new Date());
        url.setUpdatedAt(new Date());
    }

    Url mapToEntity(UrlUnauthorizedRequest request);

    List<UrlResponse> listUrlToListUrlDto(List<Url> urls);

}
