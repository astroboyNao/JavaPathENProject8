package com.tourguide.infrastructure.entities;

import com.tourguide.domain.Attraction;
import com.tourguide.domain.VisitedLocation;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
@Data
@Builder
@AllArgsConstructor
@Jacksonized
public class Reward {

    public VisitedLocation visitedLocation;
    public Attraction attraction;
    private int rewardPoints;

}
