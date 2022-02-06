package com.tripmaster.pricer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.test.StepVerifier;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.List;
import java.util.UUID;

public class PricerServiceTest {
    @Mock
    private TripPricer pricer;
    @InjectMocks
    private PricerService service;

    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getPrice() {
        Provider provider = new Provider(UUID.randomUUID(), "test", 1.0f);
        Mockito
                .when(pricer.getPrice(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
                .thenReturn(List.of(provider));

        StepVerifier.create(
                service.getProvider("test", UUID.randomUUID(), 2, 3, 4, 5)
        ).expectNext(provider);
    }

}
