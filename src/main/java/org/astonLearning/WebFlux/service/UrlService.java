package org.astonLearning.WebFlux.service;

import org.astonLearning.WebFlux.mapper.UrlToDto;
import org.astonLearning.WebFlux.model.dto.request.UrlRequestDto;
import org.astonLearning.WebFlux.model.dto.response.UrlResponseDto;
import org.astonLearning.WebFlux.repositories.UrlRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    private final GenerateTokenFromUrl generateTokenFromUrl;

    public UrlService(UrlRepository urlRepository, GenerateTokenFromUrl generateTokenFromUrl) {
        this.urlRepository = urlRepository;
        this.generateTokenFromUrl = generateTokenFromUrl;
    }

    public Mono<UrlResponseDto> createUrl(UrlRequestDto urlRequestDto) {
        return urlRepository.findByUrl(urlRequestDto.getUrl())
                .map(UrlToDto::urlToUrlResponseDto)
                .switchIfEmpty(urlRepository.save(UrlToDto.urlRequestDtoToUrl(urlRequestDto))
                        .map(url -> {
                            url.setToken(generateTokenFromUrl.getToken(url.getUrl()));
                            return url;
                        })
                        .flatMap(url -> this.urlRepository.save(url))
                        .map(UrlToDto::urlToUrlResponseDto)
                );
    }

    public Flux<UrlResponseDto> getAll() {
        return urlRepository.findAll()
                .map(UrlToDto::urlToUrlResponseDto);
    }

    public Mono<UrlResponseDto> getUrl(String shortUrl) {
        return urlRepository.findByToken(shortUrl)
                .map(UrlToDto::urlToUrlResponseDto);
    }

    public Mono<Void> deleteUrl(Long id) {
        return urlRepository.deleteById(id);
    }

    public Mono<UrlResponseDto> updateUrl(Long id, UrlRequestDto urlRequestDto) {
        return urlRepository.findById(id)
                .map(url -> {
                    url.setDateEnd(urlRequestDto.getDateEnd());
                    urlRepository.save(url);
                    return UrlToDto.urlToUrlResponseDto(url);
                });
    }

//    @Scheduled(cron = "${interval-in-cron}")
    @Scheduled(fixedRate = 5)
    @Transactional
    public Mono<Void> deleteOldUrl() {
        LocalDate ld = LocalDate.now().minusDays(1);
        System.out.println(ld);

        urlRepository.deleteByDateEndBefore(ld).subscribe(l-> System.out.println(l));
        return Mono.empty();
    }
}
