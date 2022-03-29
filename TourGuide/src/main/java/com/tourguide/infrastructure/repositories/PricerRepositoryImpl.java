package com.tourguide.infrastructure.repositories;

import com.tourguide.domain.Provider;
import com.tourguide.infrastructure.entities.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * The type Pricer repository.
 */
@Repository
public class PricerRepositoryImpl implements PricerRepository {

    private WebClient client;

    public PricerRepositoryImpl(@Qualifier("pricer-client") WebClient client){
        this.client = client;
    }

    @Override
    public Flux<Provider> getPriceForUser(String apiKey, User user, int cumulatativeRewardPoints) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/provider/" + user.getUserId())
                        .queryParam("apiKey", apiKey)
                        .queryParam("nbAdults", user.getUserPreferences().getNumberOfAdults())
                        .queryParam("nbChildren", user.getUserPreferences().getNumberOfChildren())
                        .queryParam("nightsStay", user.getUserPreferences().getTripDuration())
                        .queryParam("rewardsPoints", cumulatativeRewardPoints)
                        .build())
                .retrieve()
                .bodyToFlux(Provider.class).timeout(Duration.ofMinutes(1)).retry();
    }
}
