package com.tourguide.infrastructure.controller;


import com.tourguide.infrastructure.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.tourguide.application.service.TourGuideAdapter;

/**
 * The type Tour guide controller.
 */
@RestController
@AllArgsConstructor
public class TourGuideController {

    /**
     * The Tour guide service.
     */
    TourGuideAdapter tourGuideAdapter;


    /**
     * Gets location.
     *
     * @param userName the user name
     * @return the location
     */
    @RequestMapping("/getLocation")
    public Mono<VisitedLocation> getLocation(@RequestParam String userName) {
        return tourGuideAdapter.getUserLocation(userName);
    }

    /**
     * Gets nearby attractions.
     *
     * @param userName the user name
     * @return the nearby attractions
     */
    //  Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
    //  Return a new JSON object that contains:
    // Name of Tourist attraction,
    // Tourist attractions lat/long,
    // The user's location lat/long,
    // The distance in miles, en km between the user's location and each of the attractions.
    // The reward points for visiting each Attraction.
    //    Note: Attraction reward points can be gathered from RewardsCentral
    @RequestMapping("/getNearbyAttractions")
    public Flux<Attraction> getNearbyAttractions(@RequestParam String userName) {
        return tourGuideAdapter.getNearByAttractions(userName);
    }

    /**
     * Gets rewards.
     *
     * @param userName the user name
     * @return the rewards
     */
    @RequestMapping("/getRewards")
    public Flux<Reward> getRewards(@RequestParam String userName) {
        return tourGuideAdapter.getUserRewards(userName);
    }

    /**
     * Gets all current locations.
     *
     * @return the all current locations
     */
    @RequestMapping("/getAllCurrentLocations")
    public Flux<Location> getAllCurrentLocations() {
        return tourGuideAdapter.getAllCurrentLocation();
    }

    /**
     * Gets trip deals.
     *
     * @param userName the user name
     * @return the trip deals
     */
    @RequestMapping("/getTripDeals")
    public Flux<Provider> getTripDeals(@RequestParam String userName) {
        return tourGuideAdapter.getTripDeals(userName);
    }


}