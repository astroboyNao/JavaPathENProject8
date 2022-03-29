package com.tourguide.application.service;

import com.tourguide.domain.VisitedLocation;
import com.tourguide.infrastructure.entities.Attraction;
import com.tourguide.infrastructure.entities.User;
import com.tourguide.infrastructure.mapper.AttractionMapper;
import com.tourguide.infrastructure.repositories.GpsRepository;
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
import com.tourguide.infrastructure.mapper.VisitedLocationMapper;

import java.util.UUID;

public class GpsServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    GpsRepository gpsRepository;
    @Mock
    AttractionMapper attractionMapper;
    @Mock
    VisitedLocationMapper visitedLocationMapper;

    @InjectMocks
    GpsAdapter gpsAdapter;

    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserLocation() {
        User user = User.builder().userName("userName").userId(UUID.randomUUID()).build();
        com.tourguide.domain.User userDomain = com.tourguide.domain.User.builder().userName("userName").build();
        com.tourguide.infrastructure.entities.VisitedLocation visitedLocation = com.tourguide.infrastructure.entities.VisitedLocation.builder().build();
        VisitedLocation visitedLocationDomain = VisitedLocation.builder().build();

        Mockito.when(userRepository.get(user.getUserName())).thenReturn(Mono.just(user));
        Mockito.when(gpsRepository.getVisitedLocationForUser(user.getUserId())).thenReturn(Mono.just(visitedLocation));
        Mockito.when(visitedLocationMapper.toVisitedLocationDomain(visitedLocation)).thenReturn(visitedLocationDomain);

        StepVerifier.create(gpsAdapter.getUserLocation(userDomain)).expectNext(visitedLocationDomain).expectComplete().verify();

    }



}
