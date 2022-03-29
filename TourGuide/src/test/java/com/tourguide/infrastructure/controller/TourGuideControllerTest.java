package com.tourguide.infrastructure.controller;

import com.tourguide.application.Application;
import com.tourguide.application.service.TourGuideAdapter;
import com.tourguide.domain.*;
import com.tourguide.infrastructure.config.ApplicationConfig;
import com.tourguide.infrastructure.entities.Attraction;
import com.tourguide.infrastructure.entities.Location;
import com.tourguide.infrastructure.entities.Provider;
import com.tourguide.infrastructure.entities.Reward;
import com.tourguide.infrastructure.entities.User;
import com.tourguide.infrastructure.entities.VisitedLocation;
import com.tourguide.infrastructure.mapper.*;
import com.tourguide.infrastructure.repositories.UserRepository;
import org.hamcrest.core.AnyOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tourguide.domain.*;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = TourGuideController.class,excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
@ContextConfiguration(classes = {Application.class, ApplicationConfig.class})
@TestPropertySource("classpath:applicationTest.properties")
public class TourGuideControllerTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    TourGuideAdapter tourGuideAdapter;


    @Test
    public void getLocation() {
        Mockito.when(tourGuideAdapter.getUserLocation("userName")).thenReturn(Mono.just(VisitedLocation.builder().build()));

        webClient.get()
                .uri("/getLocation?userName=userName")
                .exchange()
                .expectStatus().is2xxSuccessful();

        Mockito.verify(tourGuideAdapter, Mockito.times(1)).getUserLocation("userName");
    }


    @Test
    public void getNearbyAttractions() {
        Mockito.when(tourGuideAdapter.getNearByAttractions("userName")).thenReturn(Flux.just(Attraction.builder().build()));

        webClient.get()
                .uri("/getNearbyAttractions?userName=userName")
                .exchange()
                .expectStatus().is2xxSuccessful();

        Mockito.verify(tourGuideAdapter, Mockito.times(1)).getNearByAttractions("userName");
    }

    @Test
    public void getRewards() {
        Mockito.when(tourGuideAdapter.getUserRewards("userName")).thenReturn(Flux.just(Reward.builder().build()));

        webClient.get()
                .uri("/getRewards?userName=userName")
                .exchange()
                .expectStatus().is2xxSuccessful();

        Mockito.verify(tourGuideAdapter, Mockito.times(1)).getUserRewards("userName");
    }

    @Test
    public void getAllCurrentLocations() {
        Mockito.when(tourGuideAdapter.getAllCurrentLocation()).thenReturn(Flux.just(Location.builder().build()));

        webClient.get()
                .uri("/getAllCurrentLocations")
                .exchange()
                .expectStatus().is2xxSuccessful();

        Mockito.verify(tourGuideAdapter, Mockito.times(1)).getAllCurrentLocation();
    }

    @Test
    public void getTripDeals() {
        Mockito.when(tourGuideAdapter.getTripDeals("userName")).thenReturn(Flux.just(Provider.builder().build()));

        webClient.get()
                .uri("/getTripDeals?userName=userName")
                .exchange()
                .expectStatus().is2xxSuccessful();

        Mockito.verify(tourGuideAdapter, Mockito.times(1)).getTripDeals("userName");
    }



}