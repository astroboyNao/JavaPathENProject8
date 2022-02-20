package tourguide.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import tourguide.domain.Provider;

/**
 * The type Pricer repository.
 */
@Repository
public class PricerRepositoryImpl implements PricerRepository {

    @Qualifier("pricer-client")
    private WebClient client;

    @Override
    public Flux<Provider> getPriceForUser(String apiKey, tourguide.infrastructure.entities.User user, int cumulatativeRewardPoints) {
        return client.get()
                .uri(uriBuilder -> uriBuilder.path("/provider/" + user.getUserId())
                        .queryParam("apiKey", apiKey)
                        .queryParam("nbAdults", user.getUserPreferences().getNumberOfAdults())
                        .queryParam("nbChildren", user.getUserPreferences().getNumberOfChildren())
                        .queryParam("nightsStay", user.getUserPreferences().getTripDuration())
                        .queryParam("rewardsPoints", cumulatativeRewardPoints)
                        .build())
                .retrieve()
                .bodyToFlux(Provider.class);
    }
}
