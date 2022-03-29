package com.tourguide.domain;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class AttractionTest {

    @Test
    public void instanciate() {
        Attraction attraction = Attraction.builder().build();
        Assert.notNull(attraction, () -> "instanciate attraction");
    }

}
