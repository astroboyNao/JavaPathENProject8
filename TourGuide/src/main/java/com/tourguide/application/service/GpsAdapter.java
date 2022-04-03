package com.tourguide.application.service;

import com.tourguide.domain.User;
import com.tourguide.domain.VisitedLocation;
import com.tourguide.domain.service.Gps;
import com.tourguide.infrastructure.mapper.AttractionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.tourguide.domain.Attraction;
import com.tourguide.infrastructure.mapper.VisitedLocationMapper;
import com.tourguide.infrastructure.repositories.GpsRepository;
import com.tourguide.infrastructure.repositories.UserRepository;
import springfox.documentation.annotations.Cacheable;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class GpsAdapter implements Gps {
    private UserRepository userRepository;
    private GpsRepository gpsRepository;
    private AttractionMapper attractionMapper;
    private VisitedLocationMapper visitedLocationMapper;
    private static List<Attraction> attractionCache;

    @Override
    public Mono<VisitedLocation> getUserLocation(User user) {
        return userRepository.get(user.getUserName()).flatMap(
                        userEntities -> gpsRepository.getVisitedLocationForUser(userEntities.getUserId()))
                .map(visitedLocation -> visitedLocationMapper.toVisitedLocationDomain(visitedLocation));
    }

    @Override
    public Flux<Attraction> getAttractions() {
        return gpsRepository.getAttractions()
                .map(attraction -> attractionMapper.toAttractionDomain(attraction));
    }

}
