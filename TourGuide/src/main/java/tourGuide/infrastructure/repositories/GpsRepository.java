package tourguide.infrastructure.repositories;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tourguide.domain.VisitedLocation;
import tourguide.infrastructure.entities.Attraction;

import java.util.UUID;

/**
 * The interface Gps repository.
 */
public interface GpsRepository {
    /**
     * Gets visited location for user.
     *
     * @param userUUID the user uuid
     * @return the visited location for user
     */
    Mono<VisitedLocation> getVisitedLocationForUser(UUID userUUID);

    /**
     * Gets attractions.
     *
     * @return the attractions
     */
    Flux<Attraction> getAttractions();
}
