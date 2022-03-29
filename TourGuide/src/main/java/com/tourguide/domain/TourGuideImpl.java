package com.tourguide.domain;

import com.tourguide.domain.service.Gps;
import com.tourguide.domain.service.Pricer;
import com.tourguide.domain.service.RewardsCalculator;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


/**
 * The type Tour guide.
 */
@AllArgsConstructor
public class TourGuideImpl implements TourGuide {
    private Gps gps;
    private Pricer pricer;
    private RewardsCalculator rewardsCalculator;
    public static final Duration TTL = Duration.ofDays(1);
    /**
     * Gets near attractions by user.
     *
     * @param user the user
     * @return the near attractions by user
     */
    @Override
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
    @Override
    public Mono<VisitedLocation> getUserLocation(User user) {
        return gps.getUserLocation(user);
    }

    /**
     * Gets user trip deals.
     *
     * @param user the user
     * @return the user trip deals
     */
    @Override
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
    @Override
    public Mono<User> trackUserLocation(User user) {
        return Mono.zip(
                gps.getAttractions().collectList(),
                gps.getUserLocation(user)).flatMap(
                objects -> {
                    return rewardsCalculator.calculateRewards(user.addToVisitedLocations(objects.getT2()), objects.getT1());
                }
        );
    }

}
