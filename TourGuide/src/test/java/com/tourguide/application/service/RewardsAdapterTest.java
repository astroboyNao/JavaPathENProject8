package com.tourguide.application.service;

import com.tourguide.infrastructure.entities.Attraction;
import com.tourguide.infrastructure.entities.User;
import com.tourguide.infrastructure.repositories.GpsRepository;
import com.tourguide.infrastructure.repositories.RewardsRepository;
import com.tourguide.infrastructure.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class RewardsAdapterTest {
    @Mock
    UserRepository userRepository;
    @Mock
    RewardsRepository rewardRepository;
    @Mock
    GpsRepository gpsRepository;

    @InjectMocks
    RewardsAdapter rewardsService;


    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRewardPoints() {

        User user = User.builder().userName("userName").build();
        Attraction attraction = Attraction.builder().attractionName("attractionName").build();
        com.tourguide.domain.Attraction attractionDomain = com.tourguide.domain.Attraction.builder().attractionName("attractionName").build();

        Mockito.when(userRepository.get(user.getUserName())).thenReturn(Mono.just(user));
        Mockito.when(gpsRepository.getAttractions()).thenReturn(Flux.just(attraction));
        Mockito.when(rewardRepository.getRewardPointsForAttractionUser(attraction, user)).thenReturn(Mono.just(1));

        StepVerifier.create(rewardsService.getRewardPoints(attractionDomain, com.tourguide.domain.User.builder().userName("userName").build())).expectNext(1).expectComplete().verify();
    }

}
