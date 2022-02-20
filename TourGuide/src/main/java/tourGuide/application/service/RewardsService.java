package tourguide.application.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;
import tourguide.domain.service.RewardsCalculator;
import tourguide.infrastructure.repositories.GpsRepository;
import tourguide.infrastructure.repositories.RewardsRepository;
import tourguide.infrastructure.repositories.UserRepository;


@Service
@AllArgsConstructor
public class RewardsService implements RewardsCalculator {
    private UserRepository userRepository;
    private RewardsRepository rewardRepository;
    private GpsRepository gpsRepository;

    @Qualifier("reward-client")
    private WebClient client;


    @Override
    public Mono<Integer> getRewardPoints(tourguide.domain.Attraction attraction, tourguide.domain.User user) {

        return Mono.zip(
                    userRepository.get(user.getUserName()),
                    gpsRepository.getAttractions().filter(attraction1 -> attraction1.getAttractionName().equals(attraction.getAttractionName())).singleOrEmpty()
                ).flatMap(objects -> rewardRepository.getRewardPointsForAttractionUser(objects.getT2(), objects.getT1()));
    }

}
