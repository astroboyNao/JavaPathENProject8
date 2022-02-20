package tourguide.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@Getter
public class Reward {

    public VisitedLocation visitedLocation;
    public Attraction attraction;
    private int rewardPoints;

}
