package com.tripmaster.pricer.controller;

import com.tripmaster.pricer.service.PricerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import tripPricer.Provider;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PricerController {
    private PricerService pricerService;

    @GetMapping("/provider/{userId}")
    public Flux<Provider> getProvider(@RequestParam(name = "apiKey") String apiKey,
                                      @PathVariable(name = "userId") UUID userId,
                                      @RequestParam(name = "nbAdults") int adults,
                                      @RequestParam(name = "nbChildren") int children,
                                      @RequestParam(name = "nightsStay") int nightsStay,
                                      @RequestParam(name = "rewardsPoints") int rewardsPoints) {
        return pricerService.getProvider(apiKey, userId, adults, children, nightsStay, rewardsPoints);
    }

}
