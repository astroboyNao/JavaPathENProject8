package com.tourguide.application.service;

import com.tourguide.infrastructure.entities.Location;
import com.tourguide.infrastructure.entities.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.tourguide.infrastructure.entities.VisitedLocation;
import com.tourguide.infrastructure.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;

/**
 * The type User service.
 */
@Service
@Log
public class UserService {
    private int internalUserNumber;
    private UserRepository userRepository;
    private final boolean testMode;

    public  UserService(@Value("${testMode:true}") boolean testMode, @Value("${internalUserNumber:100}")int internalUserNumber, UserRepository userRepository) {
        this.internalUserNumber = internalUserNumber;
        this.testMode = testMode;
        this.userRepository = userRepository;
    }
    /**
     * Init.
     */
    @PostConstruct
    public void init() {

        if (testMode) {
            log.info("TestMode enabled");
            log.info("Initializing users");
            initializeInternalUsers();
            log.info("Finished initializing users");
        }
    }

    private void initializeInternalUsers() {
        IntStream.range(0, internalUserNumber).forEach(i -> {
            String userName = "internalUser" + i;
            String phone = "000";
            String email = userName + "@tourGuide.com";
            User user = User.builder()
                    .userId(UUID.randomUUID())
                    .userName(userName)
                    .phoneNumber(phone)
                    .emailAddress(email)
                    .visitedLocations(generateUserLocationHistory())
                    .build();

            userRepository.add(userName, user);
        });
        log.info("Created " + internalUserNumber + " internal test users.");
    }

    private List<VisitedLocation> generateUserLocationHistory() {
        List<VisitedLocation> visitedLocations = new ArrayList<>();

        IntStream.range(0, 3).forEach(i -> {
                    visitedLocations.add(VisitedLocation.builder()
                            .location(Location.builder().latitude(generateRandomLatitude()).longitude(generateRandomLongitude()).build())
                            .timeVisited(getRandomTime()).build());
        });
        return visitedLocations;
    }

    private double generateRandomLongitude() {
        double leftLimit = -180;
        double rightLimit = 180;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    private double generateRandomLatitude() {
        double leftLimit = -85.05112878;
        double rightLimit = 85.05112878;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    private Date getRandomTime() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }
}
