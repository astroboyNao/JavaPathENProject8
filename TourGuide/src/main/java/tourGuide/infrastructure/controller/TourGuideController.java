package tourguide.infrastructure.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tourguide.application.service.TourGuideService;
import tourguide.domain.*;

/**
 * The type Tour guide controller.
 */
@RestController
public class TourGuideController {

    /**
     * The Tour guide service.
     */
    @Autowired
    TourGuideService tourGuideService;


    /**
     * Gets location.
     *
     * @param userName the user name
     * @return the location
     */
    @RequestMapping("/getLocation")
    public Mono<VisitedLocation> getLocation(@RequestParam String userName) {
        return tourGuideService.getUserLocation(userName);
    }

    /**
     * Gets nearby attractions.
     *
     * @param userName the user name
     * @return the nearby attractions
     */
//  TODO: Change this method to no longer return a List of Attractions.
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
        return tourGuideService.getNearByAttractions(userName);
    }

    /**
     * Gets rewards.
     *
     * @param userName the user name
     * @return the rewards
     */
    @RequestMapping("/getRewards")
    public Flux<Reward> getRewards(@RequestParam String userName) {
        return tourGuideService.getUserRewards(userName);
    }

    /**
     * Gets all current locations.
     *
     * @return the all current locations
     */
    @RequestMapping("/getAllCurrentLocations")
    public Flux<Location> getAllCurrentLocations() {
        // TODO: Get a list of every user's most recent location as JSON
        //- Note: does not use gpsUtil to query for their current location,
        //        but rather gathers the user's current location from their stored location history.
        //
        // Return object should be the just a JSON mapping of userId to Locations similar to:
        //     {
        //        "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371}
        //        ...
        //     }

        return Flux.empty();
    }

    /**
     * Gets trip deals.
     *
     * @param userName the user name
     * @return the trip deals
     */
    @RequestMapping("/getTripDeals")
    public Flux<Provider> getTripDeals(@RequestParam String userName) {
        return tourGuideService.getTripDeals(userName);
    }


}