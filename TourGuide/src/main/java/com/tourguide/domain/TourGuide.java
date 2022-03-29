package com.tourguide.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TourGuide {
    Flux<Attraction> getNearAttractionsByUser(User user);


    Mono<VisitedLocation> getUserLocation(User user);

    Flux<Provider> getUserTripDeals(User user);

    Mono<User> trackUserLocation(User user);
}
