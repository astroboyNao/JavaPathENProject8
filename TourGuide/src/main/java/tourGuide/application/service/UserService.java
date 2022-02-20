package tourguide.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tourguide.domain.Location;
import tourguide.domain.VisitedLocation;
import tourguide.infrastructure.entities.User;
import tourguide.infrastructure.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * The type User service.
 */
@Service
public class UserService {

    private static final int internalUserNumber = 100;
    private final Logger logger = LoggerFactory.getLogger(TrackerService.class);
    private UserRepository userRepository;
    private final boolean testMode = true;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {

        if (testMode) {
            logger.info("TestMode enabled");
            logger.debug("Initializing users");
            initializeInternalUsers();
            logger.debug("Finished initializing users");
        }
    }

    private void initializeInternalUsers() {
        IntStream.range(0, internalUserNumber).forEach(i -> {
            String userName = "internalUser" + i;
            String phone = "000";
            String email = userName + "@tourGuide.com";
            User user = User.builder().userId(UUID.randomUUID()).userName(userName).phoneNumber(phone).emailAddress(email).build();
            generateUserLocationHistory(user);

            userRepository.add(userName, user);
        });
        logger.debug("Created " + internalUserNumber + " internal test users.");
    }

    private void generateUserLocationHistory(User user) {
        IntStream.range(0, 3).forEach(i -> {
            user.addToVisitedLocations(
                    VisitedLocation.builder()
                            .user(user)
                            .location(Location.builder().latitude(generateRandomLatitude()).longitude(generateRandomLongitude()).build())
                            .timeVisited(getRandomTime()).build());
        });
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
