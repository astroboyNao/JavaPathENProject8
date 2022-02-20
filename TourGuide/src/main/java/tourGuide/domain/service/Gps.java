package tourguide.domain.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tourguide.domain.Attraction;
import tourguide.domain.User;
import tourguide.domain.VisitedLocation;

/**
 * The interface Gps.
 */
@Service
public interface Gps {
    /**
     * Gets user location.
     *
     * @param user the user
     * @return the user location
     */
    Mono<VisitedLocation> getUserLocation(User user);

    /**
     * Gets attractions.
     *
     * @return the attractions
     */
    Flux<Attraction> getAttractions();
}
