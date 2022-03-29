package com.tourguide.infrastructure.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourguide.application.Application;
import com.tourguide.domain.Provider;
import com.tourguide.infrastructure.config.ApplicationConfig;
import com.tourguide.infrastructure.entities.Attraction;
import com.tourguide.infrastructure.entities.User;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;


@ContextConfiguration(classes = {Application.class, ApplicationConfig.class})
public class RewardsRepositoryImplTest {
    private ObjectMapper objectMapper;

    public static MockWebServer mockBackEnd;
    private RewardsRepositoryImpl rewardsRepositoryImpl;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    public void init() {
        WebClient webClient = WebClient.builder().baseUrl(mockBackEnd.getHostName() + "/" + mockBackEnd.getPort())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        objectMapper = new ObjectMapper();
        rewardsRepositoryImpl = new RewardsRepositoryImpl(webClient);
    }

    @Test
    public void getRewardPointsForAttractionUser() throws JsonProcessingException {
        Integer rewardPoints = 10;
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(rewardPoints))
                .addHeader("Content-Type", "application/json"));

        StepVerifier.create(rewardsRepositoryImpl.getRewardPointsForAttractionUser(Attraction.builder().build(), User.builder().build()))
                .expectNextMatches(rewardPoints1 ->rewardPoints.equals(rewardPoints1));
    }
}