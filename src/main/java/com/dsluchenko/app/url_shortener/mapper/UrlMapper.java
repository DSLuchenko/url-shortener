package com.dsluchenko.app.url_shortener.mapper;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UrlMapper {

    @Mapping(source = "shortName",target = "uri")
    UrlDto urlToUrlDto(Url url);

}
