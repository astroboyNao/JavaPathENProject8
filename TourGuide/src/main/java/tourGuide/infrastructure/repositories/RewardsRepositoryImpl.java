package tourguide.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * The type Rewards repository.
 */
@Repository
public class RewardsRepositoryImpl implements RewardsRepository {

    @Qualifier("reward-client")
    private WebClient client;

    @Override
    public Mono<Integer> getRewardPointsForAttractionUser(tourguide.domain.Attraction attraction, tourguide.infrastructure.entities.User user) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/attractionRewardPoints/")
                        .queryParam("userId", user.getUserId())
                        .queryParam("attractionId", attraction)
                        .build())
                .retrieve()
                .bodyToMono(Integer.class);
    }
}
