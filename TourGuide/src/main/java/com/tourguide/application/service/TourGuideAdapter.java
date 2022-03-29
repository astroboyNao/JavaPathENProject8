package com.tourguide.application.service;

import com.tourguide.domain.TourGuide;
import com.tourguide.infrastructure.entities.*;
import com.tourguide.domain.service.Gps;
import com.tourguide.domain.service.Pricer;
import com.tourguide.domain.service.RewardsCalculator;
import com.tourguide.infrastructure.entities.User;
import com.tourguide.infrastructure.mapper.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.tourguide.infrastructure.repositories.UserRepository;

import java.util.List;

/**
 * The type Tour guide service.
 */
@Service
@AllArgsConstructor
public class TourGuideAdapter {

    private UserMapper userMapper;
    private VisitedLocationMapper visitedLocationMapper;
    private UserRepository userRepository;
    private AttractionMapper attractionMapper;
    private RewardMapper rewardMapper;
    private ProviderMapper providerMapper;
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
                .flatMap(user -> tourGuide.getUserLocation(user))
                .map(visitedLocationMapper::toVisitedLocation)
                ;
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
                .flatMapMany(user -> tourGuide.getNearAttractionsByUser(user))
                .map(attractionMapper::toAttractionEntities);
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
                .flatMapMany(user -> Flux.fromIterable(user.getRewards()))
                .map(rewardMapper::toRewardEntities);
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
                .flatMapMany(user -> tourGuide.getUserTripDeals(user))
                .map(providerMapper::toProviderEntities);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<com.tourguide.domain.User> trackUserLocation(User user) {
        return tourGuide.trackUserLocation(userMapper.toUserDomain(user))
                .doOnNext(userTracked -> userRepository.save(userMapper.toUserEntities(userTracked)));
    }

    public Flux<Location> getAllCurrentLocation(){
        return Flux.fromStream(userRepository.findAll().stream())
                .map(user -> userMapper.toUserDomain(user))
                .flatMap(user -> tourGuide.getUserLocation(user))
                .map(visitedLocationMapper::toVisitedLocation)
                .map(VisitedLocation::getLocation);
    }
}