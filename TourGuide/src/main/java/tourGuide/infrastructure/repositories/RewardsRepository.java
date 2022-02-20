package tourguide.infrastructure.repositories;

import reactor.core.publisher.Mono;

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
    Mono<Integer> getRewardPointsForAttractionUser(tourguide.domain.Attraction attraction, tourguide.infrastructure.entities.User user);
}
