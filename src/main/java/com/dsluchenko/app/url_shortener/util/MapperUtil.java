package com.dsluchenko.app.url_shortener.util;

import com.dsluchenko.app.url_shortener.dto.UrlDto;
import com.dsluchenko.app.url_shortener.entity.Url;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    public UrlDto mapToUrlDto(Url url) {
        return new UrlDto(url.getTargetUrl(), url.getShortName());
    }

}
