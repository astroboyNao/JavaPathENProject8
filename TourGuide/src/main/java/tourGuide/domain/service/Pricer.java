package tourguide.domain.service;

import reactor.core.publisher.Flux;
import tourguide.domain.Provider;
import tourguide.domain.User;

/**
 * The interface Pricer.
 */
public interface Pricer {
    /**
     * Gets price.
     *
     * @param user                     the user
     * @param cumulatativeRewardPoints the cumulatative reward points
     * @return the price
     */
    Flux<Provider> getPrice(User user, int cumulatativeRewardPoints);
}
