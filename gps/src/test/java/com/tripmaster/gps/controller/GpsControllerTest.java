package com.tripmaster.gps.controller;

import com.tripmaster.gps.service.GpsService;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = GpsController.class)
@WithMockUser
public class GpsControllerTest {

    @MockBean
    ReactiveUserDetailsService reactiveUserDetailsService;

    @MockBean
    GpsService gpsService;

    @Autowired
    private WebTestClient client;

    @Test
    void getUserLocation() {
        VisitedLocation visitedLocation = Mockito.mock(VisitedLocation.class);
        Mockito
                .when(gpsService.getUserLocation(ArgumentMatchers.any()))
                .thenReturn(Mono.just(visitedLocation));

        client.get()
                .uri("/api/location/user/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    void getAttractions() {
        Attraction attraction = Mockito.mock(Attraction.class);
        Mockito
                .when(gpsService.getAttractions())
                .thenReturn(Flux.just(attraction));

        client.get()
                .uri("/api/attractions/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();
    }
}
