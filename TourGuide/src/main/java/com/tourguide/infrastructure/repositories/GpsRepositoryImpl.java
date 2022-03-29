package com.tourguide.infrastructure.repositories;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.tourguide.infrastructure.entities.Attraction;
import com.tourguide.infrastructure.entities.VisitedLocation;
import springfox.documentation.annotations.Cacheable;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * The type Gps repository.
 */
@Repository
public class GpsRepositoryImpl implements GpsRepository {

    private WebClient client;

    public GpsRepositoryImpl(@Qualifier("gps-client") WebClient client){
        this.client = client;
    }
    private List<Attraction> attractionCache;
    public Mono<VisitedLocation> getVisitedLocationForUser(UUID userUUID) {
        return client.get()
                .uri("/location/user/" + userUUID)
                .retrieve()
                .bodyToMono(VisitedLocation.class).timeout(Duration.ofMinutes(1)).retry();
    }

    @PostConstruct
    public void initCache() {
        attractionCache = client.get().uri("attractions").retrieve().bodyToFlux(Attraction.class).collectList().block();
    }

    public Flux<Attraction> getAttractions() {
        if(attractionCache ==null) {
            attractionCache = client.get().uri("attractions").retrieve().bodyToFlux(Attraction.class).collectList().block();
        }
        return Flux.fromStream(attractionCache.stream());
    }
}
