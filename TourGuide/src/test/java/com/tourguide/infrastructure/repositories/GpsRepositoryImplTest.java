package com.tourguide.infrastructure.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tourguide.application.Application;
import com.tourguide.infrastructure.config.ApplicationConfig;
import com.tourguide.infrastructure.entities.Attraction;
import com.tourguide.infrastructure.entities.Location;
import com.tourguide.infrastructure.entities.VisitedLocation;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import springfox.documentation.annotations.Cacheable;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@ContextConfiguration(classes = {Application.class, ApplicationConfig.class})
public class GpsRepositoryImplTest {
    private ObjectMapper objectMapper;

    public static MockWebServer mockBackEnd;
    private GpsRepositoryImpl gpsRepository;
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
    public void init(){
        WebClient webClient = WebClient.builder().baseUrl(mockBackEnd.getHostName() +"/"+ mockBackEnd.getPort())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        objectMapper = new ObjectMapper();
        gpsRepository = new GpsRepositoryImpl(webClient);
    }

    @Test
    public void getVisitedLocationForUser() throws JsonProcessingException {
        VisitedLocation visitedLocation = VisitedLocation.builder().location(Location.builder().latitude(1d).longitude(1d).build()).build();
        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(visitedLocation))
                .addHeader("Content-Type", "application/json"));

        StepVerifier.create(gpsRepository.getVisitedLocationForUser(UUID.randomUUID())).expectNextMatches(visitedLocation1 -> visitedLocation1.getLocation().getLatitude() == 1d);
    }

    @Test
    public void getAttractions() throws JsonProcessingException {
        Attraction attraction = Attraction.builder().attractionName("attractionName").build();

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(attraction))
                .addHeader("Content-Type", "application/json"));

        StepVerifier.create(gpsRepository.getVisitedLocationForUser(UUID.randomUUID())).expectNextMatches(attraction1 -> attraction.getAttractionName().equals("attractionName"));
    }

}
