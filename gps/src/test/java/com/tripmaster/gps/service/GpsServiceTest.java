package com.tripmaster.gps.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;
public class GpsServiceTest {
    @Mock
    private GpsUtil gpsUtil;
    @InjectMocks
    private GpsService gpsService;

    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserLocation() {
        VisitedLocation visitedLocation = Mockito.mock(VisitedLocation.class);
        Mockito
                .when(gpsUtil.getUserLocation(ArgumentMatchers.any()))
                .thenReturn(visitedLocation);

        StepVerifier.create(
                gpsService.getUserLocation(UUID.randomUUID())
        ).expectNext(visitedLocation);
    }


    @Test
    void getAttractions() {
        var attraction = Mockito.mock(Attraction.class);
        var attractions = List.of(attraction);
        Mockito
                .when(gpsUtil.getAttractions())
                .thenReturn(attractions);
        StepVerifier.create(
                gpsService.getAttractions()
        ).expectNext(attraction);
    }
}
