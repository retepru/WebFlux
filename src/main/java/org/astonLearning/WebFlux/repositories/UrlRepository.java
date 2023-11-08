package org.astonLearning.WebFlux.repositories;

import org.astonLearning.WebFlux.model.entity.Url;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface UrlRepository extends ReactiveCrudRepository<Url, Long> {
    Mono<Url> findByUrl(String url);

    Mono<Url> findByToken(String token);

    Mono<Void> deleteById(Long id);

    Mono<Long> deleteByDateEnd(LocalDate dateEnd);

    Mono<Long> deleteByDateEndBefore(LocalDate dateEnd);

    Flux<Url> findByDateEnd(LocalDate dateEnd);

//    Mono<Url> findByUrlAndIdNot(String url, Long id);

}
