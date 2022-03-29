package com.tourguide.domain;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


public class LocationTest {

    @Test
    public void getDistance() {
        Location locationFrom = Location.builder().latitude(1D).longitude(2D).build();
        Location locationTo = Location.builder().latitude(2D).longitude(3D).build();
        var distance = locationFrom.getDistance(locationTo);
        Assert.isTrue(distance > 0, "distance");
    }
}
