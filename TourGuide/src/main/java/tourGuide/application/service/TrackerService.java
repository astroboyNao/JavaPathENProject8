package tourguide.application.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tourguide.infrastructure.entities.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The type Tracker service.
 */
@Service
@AllArgsConstructor
public class TrackerService {
    private static final long trackingPollingInterval = TimeUnit.MINUTES.toSeconds(5);
    private final TourGuideService tourGuideService;
    private Logger logger = LoggerFactory.getLogger(TrackerService.class);
    private boolean stop = false;

    /**
     * Run.
     */
    @Scheduled(cron = "* 5 * * * *")
    public void run() {
        List<User> users = tourGuideService.getAllUsers();
        logger.debug("Begin Tracker. Tracking " + users.size() + " users.");
        users.forEach(u -> tourGuideService.trackUserLocation(u));
        logger.debug("End Tracker. Tracking ");
    }
}
