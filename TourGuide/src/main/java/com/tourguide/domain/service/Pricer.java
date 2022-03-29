package com.tourguide.domain.service;

import com.tourguide.domain.Provider;
import com.tourguide.domain.User;
import reactor.core.publisher.Flux;

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
