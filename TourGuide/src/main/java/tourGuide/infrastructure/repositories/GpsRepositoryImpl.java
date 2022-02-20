package tourguide.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tourguide.domain.VisitedLocation;
import tourguide.infrastructure.entities.Attraction;

import java.util.UUID;


/**
 * The type Gps repository.
 */
@Repository
public class GpsRepositoryImpl implements GpsRepository {
    @Qualifier("gps-client")
    private WebClient client;

    public Mono<VisitedLocation> getVisitedLocationForUser(UUID userUUID) {
        return client.get()
                .uri("/location/user/" + userUUID)
                .retrieve()
                .bodyToMono(VisitedLocation.class);
    }

    public Flux<Attraction> getAttractions() {
        return client.get().uri("attractions").retrieve().bodyToFlux(Attraction.class);
    }
}
