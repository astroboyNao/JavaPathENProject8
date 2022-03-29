package com.tripmaster.rewardcentral.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rewardCentral.RewardCentral;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RewardCentralService {
    private RewardCentral rewardCentral;

    public Mono<Integer> getAttractionRewardPoints(UUID userId, UUID attractionId) {

      return Mono.just( rewardCentral.getAttractionRewardPoints( userId, attractionId));
    }


}
