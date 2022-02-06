package com.tripmaster.pricer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PricerService {
    private TripPricer tripPricer;

    public Flux<Provider> getProvider(String apiKey, UUID attractionId, int adults, int children, int nightsStay, int rewardsPoints) {

        return Flux.fromStream(tripPricer.getPrice(apiKey, attractionId, adults, children, nightsStay, rewardsPoints).stream());
    }


}
