package com.tourguide.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
public class Reward {

    public VisitedLocation visitedLocation;
    public Attraction attraction;
    private int rewardPoints;

}
