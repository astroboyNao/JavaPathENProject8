package com.tourguide.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.AbstractMap;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VisitedLocationTest {

    @Test
    public void nearAttractionWithSameLocation() {
        VisitedLocation visitedLocation = VisitedLocation.builder().location(Location.builder().longitude(1D).latitude(1D).build()).build();
        Attraction attraction = Attraction.builder().location(Location.builder().longitude(1D).latitude(1D).build()).build();
        Assert.isTrue(Boolean.valueOf(visitedLocation.nearAttraction(attraction)), () -> "The Location is near Attraction with same location");
    }

    @Test
    public void nearAttractionWithNearLocation() {
        VisitedLocation visitedLocation = VisitedLocation.builder().location(Location.builder().longitude(1.1D).latitude(1.1D).build()).build();
        Attraction attraction = Attraction.builder().location(Location.builder().longitude(1D).latitude(1D).build()).build();
        Assert.isTrue(Boolean.valueOf(visitedLocation.nearAttraction(attraction)), () -> "The Location is near Attraction with near location");
    }

    @Test
    public void nearAttractionWithFarLocation() {
        VisitedLocation visitedLocation = VisitedLocation.builder().location(Location.builder().longitude(1D).latitude(1D).build()).build();
        Attraction attraction = Attraction.builder().location(Location.builder().longitude(4D).latitude(4D).build()).build();
        System.out.println(visitedLocation.nearAttraction(attraction));
        Assert.isTrue(!visitedLocation.nearAttraction(attraction), () -> "The Location is not a near Attraction with far location");
    }

    @Test
    public void nearAttractionWithANearAttractionInList() {
        VisitedLocation visitedLocation = VisitedLocation.builder().location(Location.builder().longitude(1D).latitude(1D).build()).build();
        List<Attraction> attractions = List.of(
                Attraction.builder().location(Location.builder().longitude(2D).latitude(2D).build()).build(),
                Attraction.builder().location(Location.builder().longitude(1D).latitude(1D).build()).build());
        Assert.isTrue(Boolean.valueOf(visitedLocation.nearAttraction(attractions).size() == 1), () -> "One attraction is near the Location");
    }

    @Test
    public void isWithinAttractionProximity() {
        VisitedLocation visitedLocation = VisitedLocation.builder().location(Location.builder().longitude(1D).latitude(1D).build()).build();
        Attraction attraction = Attraction.builder().location(Location.builder().longitude(2D).latitude(2D).build()).build();
        Assert.isTrue(Boolean.valueOf(visitedLocation.isWithinAttractionProximity(attraction)), () -> "The Location is within Attraction proximity");
    }
}
