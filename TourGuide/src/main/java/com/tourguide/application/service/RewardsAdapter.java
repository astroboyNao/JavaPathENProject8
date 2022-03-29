package com.tourguide.application.service;

import com.tourguide.domain.Attraction;
import com.tourguide.domain.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.tourguide.domain.service.RewardsCalculator;
import com.tourguide.infrastructure.repositories.GpsRepository;
import com.tourguide.infrastructure.repositories.RewardsRepository;
import com.tourguide.infrastructure.repositories.UserRepository;


@Service
@AllArgsConstructor
public class RewardsAdapter implements RewardsCalculator {
    private UserRepository userRepository;
    private RewardsRepository rewardRepository;
    private GpsRepository gpsRepository;


    @Override
    public Mono<Integer> getRewardPoints(Attraction attraction, User user) {

        return Mono.zip(
                    userRepository.get(user.getUserName()),
                    gpsRepository.getAttractions().filter(attraction1 -> attraction1.getAttractionName().equals(attraction.getAttractionName())).singleOrEmpty()
                ).flatMap(objects -> rewardRepository.getRewardPointsForAttractionUser(objects.getT2(), objects.getT1()));


    }

}
