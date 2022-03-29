package com.tourguide.domain;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ProviderTest {

    @Test
    public void instanciate() {
      Provider provider = Provider.builder().build();
        Assert.notNull(provider, () -> "instanciate provider");
    }

}
