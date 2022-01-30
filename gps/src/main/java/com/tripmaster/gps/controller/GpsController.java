package com.tripmaster.gps.controller;

import com.tripmaster.gps.service.GpsService;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GpsController {
    private GpsService gpsService;

    @GetMapping( "/location/user/{userId}")
    public Mono<VisitedLocation> getUserLocation(@PathVariable UUID userId) {
        return gpsService.getUserLocation(userId);
    }


    @GetMapping("/attractions")
    public Flux<Attraction> getAttractions() {
        return gpsService.getAttractions();
    }
}
