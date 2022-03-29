package com.tourguide.domain.service;

import com.tourguide.domain.*;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

public class RewardsCalculatorTest {

    @Test
    public void calculateRewards() {
        RewardsCalculator rewardsCalculator = new RewardsCalculator() {
            @Override
            public Mono<Integer> getRewardPoints(Attraction attraction, User user) {
                return Mono.just(1);
            }
        };
        User user = User.builder().visitedLocations(VisitedLocation.builder().location(Location.builder().latitude(1D).longitude(1D).build()).build()).build();
        User expectedUser = user.toBuilder().rewards(List.of(Reward.builder().rewardPoints(1).build())).build();

        StepVerifier.create(
                rewardsCalculator.calculateRewards(User.builder().build(), List.of(Attraction.builder().location(Location.builder().latitude(1D).longitude(1D).build()).build()))
        ).expectNext(expectedUser);


    }

}
