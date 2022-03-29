package com.tourguide;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.tourguide.application.Application;
import com.tourguide.application.service.*;
import com.tourguide.domain.Attraction;
import com.tourguide.domain.VisitedLocation;
import com.tourguide.infrastructure.config.ApplicationConfig;
import com.tourguide.infrastructure.entities.User;
import com.tourguide.infrastructure.mapper.UserMapper;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
/*
@ContextConfiguration(classes = {Application.class, ApplicationConfig.class})
@TestPropertySource("classpath:applicationTest.properties")
@ExtendWith(SpringExtension.class)*/
public class TestPerformance {
	@Autowired
    TourGuideAdapter tourGuideAdapter;
	@Autowired
	UserService userService;
	@Autowired
	TrackerAdapter trackerAdapter;
	@Autowired
	GpsAdapter gpsAdapter;
	@Autowired
	RewardsAdapter rewardsAdapter;
	@Autowired
	UserMapper userMapper;

	/*
	 * A note on performance improvements:
	 *
	 *     The number of users generated for the high volume tests can be easily adjusted via this method:
	 *
	 *     		InternalTestHelper.setInternalUserNumber(100000);
	 *
	 *
	 *     These tests can be modified to suit new solutions, just as long as the performance metrics
	 *     at the end of the tests remains consistent.
	 *
	 *     These are performance metrics that we are trying to hit:
	 *
	 *     highVolumeTrackLocation: 100,000 users within 15 minutes:
	 *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     *
     *     highVolumeGetRewards: 100,000 users within 20 minutes:
	 *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	 */
	//@Test
	public void highVolumeTrackLocation() throws Exception{

		userService.init();

		List<com.tourguide.infrastructure.entities.User> allUsers = tourGuideAdapter.getAllUsers();

	    StopWatch stopWatch = new StopWatch();

		var flux = Flux.fromStream(allUsers.stream())
				.flatMap( user -> tourGuideAdapter.trackUserLocation(user)
				.subscribeOn(Schedulers.boundedElastic()));

		stopWatch.start();

		StepVerifier.create(flux).expectNextCount(allUsers.stream().count()).verifyComplete();

		stopWatch.stop();

		var time = TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime());
		System.out.println("highVolumeTrackLocation: Time Elapsed: " + time + " seconds.");
		Assert.isTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

	//@Test
	public void highVolumeGetRewards() {
		// Users should be incremented up to 100,000, and test finishes within 20 minutes

		userService.init();
		Attraction attraction =  gpsAdapter.getAttractions().collectList().block().get(0);

		var allUsers = tourGuideAdapter.getAllUsers().stream()
				.map(userMapper::toUserDomain)
				.map(user -> user.addToVisitedLocations(VisitedLocation.builder().location(attraction.getLocation()).build()))
				.collect(Collectors.toList());

		StopWatch stopWatch = new StopWatch();

		var flux = Flux.fromStream(allUsers.stream())
			.flatMap(user -> rewardsAdapter.calculateRewards(user, List.of(attraction)).subscribeOn(Schedulers.boundedElastic()));

		stopWatch.start();

		StepVerifier.create(flux).expectNextCount(allUsers.stream().count()).verifyComplete();

		stopWatch.stop();


		var time = TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime());
		System.out.println("highVolumeGetRewards: Time Elapsed: " + time + " seconds." );
		Assert.isTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
	}

}
