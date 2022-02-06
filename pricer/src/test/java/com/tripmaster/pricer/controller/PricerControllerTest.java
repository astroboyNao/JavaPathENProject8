package com.tripmaster.pricer.controller;

import com.tripmaster.pricer.service.PricerService;
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
import tripPricer.Provider;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PricerController.class)
@WithMockUser
public class PricerControllerTest {

    @MockBean
    ReactiveUserDetailsService reactiveUserDetailsService;

    @MockBean
    PricerService service;

    @Autowired
    private WebTestClient client;

    @Test
    void getPrice() {
        Provider provider = new Provider(UUID.randomUUID(), "test", 1.0f);
        Mockito
                .when(service.getProvider(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(Flux.just(provider));

        client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/provider/" + UUID.randomUUID())
                        .queryParam("apiKey", "test")
                        .queryParam("nbAdults", "1")
                        .queryParam("nbChildren", "2")
                        .queryParam("nightsStay", "3")
                        .queryParam("rewardsPoints", "4")
                        .build())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk();
    }

}
