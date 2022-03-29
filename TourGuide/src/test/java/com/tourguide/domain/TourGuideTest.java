package com.tourguide.domain;

import com.tourguide.domain.service.Gps;
import com.tourguide.domain.service.Pricer;
import com.tourguide.domain.service.RewardsCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import com.tourguide.domain.*;

import java.util.List;


/**
 * The type Tour guide.
 */
public class TourGuideTest {
    @Mock
    Gps gps;
    @Mock
    Pricer pricer;
    @Mock
    RewardsCalculator rewardsCalculator;
    @InjectMocks
    TourGuideImpl tourGuide;

    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNearAttractionsByUser() {

        VisitedLocation visitedLocation = VisitedLocation.builder().location(Location.builder().latitude(1D).longitude(2D).build()).build();
        User user = User.builder().userName("userName").visitedLocations(visitedLocation).build();
        Attraction attraction = Attraction.builder().location(Location.builder().latitude(1D).longitude(2D).build()).attractionName("attractionName").build();

        Mockito.when(gps.getUserLocation(user)).thenReturn(Mono.just(visitedLocation));
        Mockito.when(gps.getAttractions()).thenReturn(Flux.just(attraction));
        StepVerifier.create(tourGuide.getNearAttractionsByUser(user)).expectNext(attraction).expectComplete().verify();

    }
    @Test
    public void getUserLocation() {

        VisitedLocation visitedLocation = VisitedLocation.builder().build();
        User user = User.builder().userName("userName").visitedLocations(visitedLocation).build();

        Mockito.when(gps.getUserLocation(user)).thenReturn(Mono.just(visitedLocation));

        StepVerifier.create(tourGuide.getUserLocation(user)).expectNext(visitedLocation).expectComplete().verify();
    }

    @Test
    public void getUserTripDeals() {
        User user = User.builder().userName("userName").userRewards(Reward.builder().rewardPoints(1).build()).build();
        Provider provider = Provider.builder().name("providerName").build();
        Mockito.when(pricer.getPrice(user, 1)).thenReturn(Flux.just(provider));
        StepVerifier.create(tourGuide.getUserTripDeals(user)).expectNext(provider).expectComplete().verify();
    }

    public void trackUserLocation() {
        Attraction attraction = Attraction.builder().attractionName("attractionName").build();
        User user = User.builder().userName("userName").userRewards(Reward.builder().rewardPoints(1).build()).build();
        VisitedLocation visitedLocation = VisitedLocation.builder().build();

        Mockito.when(gps.getUserLocation(user)).thenReturn(Mono.just(visitedLocation));
        Mockito.when(gps.getAttractions()).thenReturn(Flux.just(attraction));
        Mockito.when(rewardsCalculator.calculateRewards(user, List.of(attraction))).thenReturn(Mono.just(user));

        StepVerifier.create(tourGuide.trackUserLocation(user)).expectNext(user).expectComplete().verify();
    }
}
