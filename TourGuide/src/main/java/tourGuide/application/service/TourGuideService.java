package tourguide.application.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tourguide.domain.*;
import tourguide.infrastructure.entities.User;
import tourguide.infrastructure.mapper.UserMapper;
import tourguide.infrastructure.repositories.UserRepository;

import java.util.List;

/**
 * The type Tour guide service.
 */
@Service
@AllArgsConstructor
public class TourGuideService {

    public TrackerService tracker;
    boolean testMode = true;
    private UserMapper userMapper;
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(TourGuideService.class);
    private TourGuide tourGuide;

    /**
     * Gets user location.
     *
     * @param userName the user name
     * @return the user location
     */
    public Mono<VisitedLocation> getUserLocation(String userName) {
        return userRepository.get(userName)
                .mapNotNull(user -> userMapper.toUserDomain(user))
                .flatMap(user -> tourGuide.getUserLocation(user));
    }

    /**
     * Gets near by attractions.
     *
     * @param userName the user name
     * @return the near by attractions
     */
    public Flux<Attraction> getNearByAttractions(String userName) {
        return userRepository.get(userName)
                .mapNotNull(user -> userMapper.toUserDomain(user))
                .flatMapMany(user -> tourGuide.getNearAttractionsByUser(user));
    }

    /**
     * Gets user rewards.
     *
     * @param userName the user name
     * @return the user rewards
     */
    public Flux<Reward> getUserRewards(String userName) {
        return userRepository.get(userName)
                .mapNotNull(user -> userMapper.toUserDomain(user))
                .flatMapMany(user -> Flux.fromIterable(user.getRewards()));
    }

    /**
     * Gets trip deals.
     *
     * @param userName the user name
     * @return the trip deals
     */
    public Flux<Provider> getTripDeals(String userName) {
        return userRepository.get(userName)
                .mapNotNull(user -> userMapper.toUserDomain(user))
                .flatMapMany(user -> tourGuide.getUserTripDeals(user));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void trackUserLocation(User user) {
        return tourGuide.trackUserLocation(userMapper.toUserDomain(user)).flatMap(
                userTracked -> userRepository.save(userMapper.toUserEntities(userTracked));
    }


}