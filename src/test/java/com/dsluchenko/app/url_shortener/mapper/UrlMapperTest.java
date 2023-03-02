package com.dsluchenko.app.url_shortener.mapper;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;


public class UrlMapperTest {

    @Test
    public void shouldMapUrlToUrlDto() {
        UrlMapper mapper = Mappers.getMapper(UrlMapper.class);

        //given
        Url url = new Url("targetUrl", "shortName", new Date(), new Date());

        //when
        UrlDto urlDto = mapper.urlToUrlDto(url);

        //then
        assertNotNull(urlDto);
        assertEquals(urlDto.getTargetUrl(), url.getTargetUrl());
        assertEquals(urlDto.getUri(), url.getShortName());
    }

}
