package com.tourguide.application.service;

import com.tourguide.infrastructure.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.List;

public class TrackerAdapterTest {

    @Mock
    TourGuideAdapter tourGuideAdapter;
    @InjectMocks
    TrackerAdapter trackerAdapter;


    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void runTest() {
        User user = User.builder().userName("userName").build();
        List<User> users = List.of(user);
        Mockito.when(tourGuideAdapter.getAllUsers()).thenReturn(users);
        Mockito.when(tourGuideAdapter.trackUserLocation(user)).thenReturn(Mono.empty());
        trackerAdapter.run();

        Mockito.verify(tourGuideAdapter, Mockito.times(1)).getAllUsers();
        Mockito.verify(tourGuideAdapter, Mockito.times(1)).trackUserLocation(Mockito.any(User.class));
    }
}
