package tourguide.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tourguide.domain.service.Gps;
import tourguide.domain.service.Pricer;
import tourguide.domain.service.RewardsCalculator;


/**
 * The type Tour guide.
 */
public class TourGuide {
    private Gps gps;
    private Pricer pricer;
    private RewardsCalculator rewardsCalculator;

    /**
     * Gets near attractions by user.
     *
     * @param user the user
     * @return the near attractions by user
     */
    public Flux<Attraction> getNearAttractionsByUser(User user) {
        return this.getUserLocation(user).flatMapMany(
                visitedLocation -> this.getNearByAttractions(visitedLocation)
        );
    }

    private Flux<Attraction> getNearByAttractions(VisitedLocation visitedLocation) {
        return gps.getAttractions().filter(
                attraction -> visitedLocation.isWithinAttractionProximity(attraction)
        );
    }

    /**
     * Gets user location.
     *
     * @param user the user
     * @return the user location
     */
    public Mono<VisitedLocation> getUserLocation(User user) {
        return gps.getUserLocation(user);
    }

    /**
     * Gets user trip deals.
     *
     * @param user the user
     * @return the user trip deals
     */
    public Flux<Provider> getUserTripDeals(User user) {
        int cumulatativeRewardPoints = user.getRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();
        return pricer.getPrice(user, cumulatativeRewardPoints)
                .collectList().flatMapMany(
                        providers -> {
                            //TODO comprendre pourquoi se set
                            user.setTripDeals(providers);
                            return Flux.fromStream(providers.stream());
                        }
                );
    }

    /**
     * Track user location mono.
     *
     * @param user the user
     * @return the mono
     */
    public Mono<User> trackUserLocation(User user) {
        return Mono.zip(
                gps.getAttractions().collectList(),
                gps.getUserLocation(user)).flatMap(
                objects -> {
                    user.addToVisitedLocations(objects.getT2());
                    return rewardsCalculator.calculateRewards(user, objects.getT1());
                }
        );
    }
}
