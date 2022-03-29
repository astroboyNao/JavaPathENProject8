package com.tourguide.infrastructure.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourguide.application.Application;
import com.tourguide.domain.Provider;
import com.tourguide.infrastructure.config.ApplicationConfig;
import com.tourguide.infrastructure.entities.Preferences;
import com.tourguide.infrastructure.entities.User;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;

@ContextConfiguration(classes = {Application.class, ApplicationConfig.class})
public class PricerRepositoryImplTest {
    private ObjectMapper objectMapper;

    public static MockWebServer mockBackEnd;
    private PricerRepositoryImpl pricerRepositoryImpl;

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
        pricerRepositoryImpl = new PricerRepositoryImpl(webClient);
    }

    @Test
    public void getPriceForUser() throws JsonProcessingException {
        Provider provider = Provider.builder().name("provider").build();
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(provider))
                .addHeader("Content-Type", "application/json"));

        StepVerifier.create(pricerRepositoryImpl.getPriceForUser("apiKey", User.builder().userPreferences(Preferences.builder().build()).build(), 10))
                .expectNextMatches(provider1 -> provider.getName().equals("provider"));
    }
}
