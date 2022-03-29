package com.tourguide.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type User.
 */
@Data
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
public class User {
    private String userName;
    private String phoneNumber;
    private String emailAddress;
    private Date latestLocationTimestamp;
    private Preferences userPreferences ;

    @Singular("visitedLocations")
    private List<VisitedLocation> visitedLocations;

    @Singular("userRewards")
    private List<Reward> rewards;

    @Singular("tripDeals")
    private List<Provider> tripDeals;

    /**
     * Add to visited locations.
     *
     * @param visitedLocation the visited location
     */
    public User addToVisitedLocations(VisitedLocation visitedLocation) {
        return this.toBuilder().visitedLocations(visitedLocation).build();
    }

    /**
     * Gets visited locations.
     *
     * @return the visited locations
     */
    public List<VisitedLocation> getVisitedLocations() {
        return visitedLocations;
    }

    /**
     * Clear visited locations user.
     *
     * @return the user
     */
    public User clearVisitedLocations() {
        return this.toBuilder().clearVisitedLocations().build();
    }

    /**
     * Add reward user.
     *
     * @param reward the reward
     * @return the user
     */
    public User addReward(Reward reward) {
        if (rewards.stream().filter(r -> !r.attraction.getAttractionName().equals(reward.attraction)).count() == 0) {
            return this.toBuilder().userRewards(reward).build();
        }
        return this;
    }

    /**
     * Gets last visited location.
     *
     * @return the last visited location
     */
    public VisitedLocation getLastVisitedLocation() {
        return visitedLocations.get(visitedLocations.size() - 1);
    }


}
