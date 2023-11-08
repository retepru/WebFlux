package org.astonLearning.WebFlux.mapper;

import org.astonLearning.WebFlux.model.dto.request.UrlRequestDto;
import org.astonLearning.WebFlux.model.dto.response.UrlResponseDto;
import org.astonLearning.WebFlux.model.entity.Url;

public class UrlToDto {
    public static UrlResponseDto urlToUrlResponseDto(Url url)   {
        return new UrlResponseDto(url.getUrl(), url.getToken(), url.getDateEnd());
    }
    public static Url urlRequestDtoToUrl(UrlRequestDto dto) {
        return new Url(dto.getUrl(), dto.getDateEnd());
    }
}
