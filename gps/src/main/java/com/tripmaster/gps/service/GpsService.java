package com.tripmaster.gps.service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GpsService {
    private GpsUtil gpsUtil;

        public Mono<VisitedLocation> getUserLocation(UUID userId) {

            return Mono.just(gpsUtil.getUserLocation(userId));
    }

    public Flux<Attraction> getAttractions() {
        return Flux.fromStream(gpsUtil.getAttractions().stream());
    }
}
