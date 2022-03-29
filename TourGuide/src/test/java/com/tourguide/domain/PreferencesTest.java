package com.tourguide.domain;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class PreferencesTest {

    @Test
    public void instanciate() {
        Preferences preferences = Preferences.builder().build();
        Assert.notNull(preferences, () -> "instanciate preferences");
    }

}
