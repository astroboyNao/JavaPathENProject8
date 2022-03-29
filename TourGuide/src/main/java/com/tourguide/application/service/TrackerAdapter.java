package com.tourguide.application.service;

import com.tourguide.infrastructure.entities.User;
import lombok.AllArgsConstructor;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Tracker service.
 */
@Service
@AllArgsConstructor
@Log
public class TrackerAdapter {
    private TourGuideAdapter tourGuideAdapter;

    /**
     * Run.
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void run() {
        List<User> users = tourGuideAdapter.getAllUsers();
        log.info("Begin Tracker. Tracking " + users.size() + " users.");
        users.forEach(u -> tourGuideAdapter.trackUserLocation(u).block());
        log.info("End Tracker. Tracking ");
    }
}
