package com.tourguide.infrastructure.repositories;

import com.tourguide.infrastructure.entities.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.tourguide.infrastructure.entities.Attraction;

import java.time.Duration;


/**
 * The type Rewards repository.
 */
@Repository
public class RewardsRepositoryImpl implements RewardsRepository {

    private WebClient client;

    public RewardsRepositoryImpl(@Qualifier("reward-central-client") WebClient client){
        this.client = client;
    }

    @Override
    public Mono<Integer> getRewardPointsForAttractionUser(Attraction attraction, User user) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/attractionRewardPoints/")
                        .queryParam("userId", user.getUserId())
                        .queryParam("attractionId", attraction.getAttractionId())
                        .build())
                .retrieve()
                .bodyToMono(Integer.class).retry();
    }
}
