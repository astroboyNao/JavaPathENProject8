package tourguide.infrastructure.repositories;

import reactor.core.publisher.Flux;
import tourguide.domain.Provider;

/**
 * The interface Pricer repository.
 */
public interface PricerRepository {
    /**
     * Gets price for user.
     *
     * @param apiKey                   the api key
     * @param user                     the user
     * @param cumulatativeRewardPoints the cumulatative reward points
     * @return the price for user
     */
    Flux<Provider> getPriceForUser(String apiKey, tourguide.infrastructure.entities.User user, int cumulatativeRewardPoints);
}
