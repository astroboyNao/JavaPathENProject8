package com.tourguide.infrastructure.repositories;

import com.tourguide.infrastructure.entities.User;
import reactor.core.publisher.Mono;
import com.tourguide.infrastructure.entities.Attraction;

/**
 * The interface Rewards repository.
 */
public interface RewardsRepository {
    /**
     * Gets reward points for attraction user.
     *
     * @param attraction the attraction
     * @param user       the user
     * @return the reward points for attraction user
     */
    Mono<Integer> getRewardPointsForAttractionUser(Attraction attraction, User user);
}
