package tourguide.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type User.
 */
@Data
@SuperBuilder(toBuilder = true)
@Getter
public class User {
    private String userName;
    private String phoneNumber;
    private String emailAddress;
    private Date latestLocationTimestamp;
    private Preferences userPreferences = new Preferences();

    @Singular("visitedLocations")
    private List<VisitedLocation> visitedLocations = new ArrayList<>();

    @Singular("userRewards")
    private List<Reward> rewards = new ArrayList<>();

    @Singular("tripDeals")
    private List<Provider> tripDeals = new ArrayList<>();

    /**
     * Add to visited locations.
     *
     * @param visitedLocation the visited location
     */
    public void addToVisitedLocations(VisitedLocation visitedLocation) {
        this.getVisitedLocations().add(visitedLocation);
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
