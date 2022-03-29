package com.tourguide.domain;

import lombok.*;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTest {

    @Test
    public void addVisitedLocation (){
        User user = User.builder().build();
        user = user.addToVisitedLocations(VisitedLocation.builder().build());
        Assert.isTrue(user.getVisitedLocations().size()>0, () -> "user have one visitedLocation");
    }

    @Test
    public void clearVisitedLocations (){
        User user = User.builder().build();
        user = user.addToVisitedLocations(VisitedLocation.builder().build());
        user = user.clearVisitedLocations();
        Assert.isTrue(user.getVisitedLocations().size()==0, () -> "user have one visitedLocation");
    }

    @Test
    public void getLastVisitedLocation (){
        User user = User.builder().build();
        user = user.addToVisitedLocations(VisitedLocation.builder().location(Location.builder().latitude(1D).build()).build());
        user = user.addToVisitedLocations(VisitedLocation.builder().location(Location.builder().latitude(2D).build()).build());
        Assert.isTrue(user.getLastVisitedLocation().getLocation().getLatitude() == 2D, () -> "get last visitedLocation");
    }


    @Test
    public void addReward (){
        User user = User.builder().build();
        user = user.addReward(Reward.builder().attraction(Attraction.builder().attractionName("attractionName").build()).build());
        Assert.isTrue(user.getRewards().size() == 1, () -> "add Rewards");
    }

    @Test
    public void addOnlyDistinctReward (){
        User user = User.builder().build();
        user = user.addReward(Reward.builder().attraction(Attraction.builder().attractionName("attractionName").build()).build());
        user = user.addReward(Reward.builder().attraction(Attraction.builder().attractionName("attractionName").build()).build());
        Assert.isTrue(user.getRewards().size() == 1, () -> "add Rewards - only distinct reward by attraction name -");
    }
}
