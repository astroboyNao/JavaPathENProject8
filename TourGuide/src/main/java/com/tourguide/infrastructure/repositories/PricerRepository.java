package com.tourguide.infrastructure.repositories;

import com.tourguide.domain.Provider;
import com.tourguide.infrastructure.entities.User;
import reactor.core.publisher.Flux;

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
    Flux<Provider> getPriceForUser(String apiKey, User user, int cumulatativeRewardPoints);
}
