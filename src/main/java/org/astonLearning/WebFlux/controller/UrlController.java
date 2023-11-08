package org.astonLearning.WebFlux.controller;

import org.astonLearning.WebFlux.model.dto.request.UrlRequestDto;
import org.astonLearning.WebFlux.model.dto.response.UrlResponseDto;
import org.astonLearning.WebFlux.repositories.UrlRepository;
import org.astonLearning.WebFlux.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlService urlService;

    private final UrlRepository urlRepository;

    public UrlController(UrlService urlService, UrlRepository urlRepository) {
        this.urlService = urlService;
        this.urlRepository = urlRepository;
    }


    @PostMapping()
    public Mono<ResponseEntity<UrlResponseDto>>  createUrl(@RequestBody UrlRequestDto urlRequestDto) {
        return urlService.createUrl(urlRequestDto)
                .map(ResponseEntity::ok);
    }

    @GetMapping()
    public Flux<ResponseEntity<UrlResponseDto>> getShortUrls() {
        return urlService.getAll()
                .map(ResponseEntity::ok);
    }

    @GetMapping("{shortUrl}")
    public Mono<ResponseEntity<UrlResponseDto>> getShortUrl(@PathVariable String shortUrl) {
        return urlService.getUrl(shortUrl)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<UrlResponseDto>> updateUrl(@PathVariable Long id,
                                       @RequestBody UrlRequestDto urlRequestDto){
        return urlService.updateUrl(id, urlRequestDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteUrl(@PathVariable Long id){
        return urlService.deleteUrl(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
    }

    @GetMapping("deleteOldUrl")
    public Mono<Void> deleteOldUrl() {

//        return urlService.deleteOldUrl();
        return Mono.empty();
    }

}

