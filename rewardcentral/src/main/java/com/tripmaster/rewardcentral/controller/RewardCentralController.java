package com.tripmaster.rewardcentral.controller;

import com.tripmaster.rewardcentral.service.RewardCentralService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RewardCentralController {
    private RewardCentralService service;

    @GetMapping( "/attractionRewardPoints")
        public Mono<Integer> getAttractionRewardPoints(@RequestParam(name="userId") UUID userId,
                                                   @RequestParam(name="attractionId") UUID attractionId) {
        return service.getAttractionRewardPoints(userId, attractionId);
    }

}
