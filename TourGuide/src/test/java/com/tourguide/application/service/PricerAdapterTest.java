package com.tourguide.application.service;

import com.tourguide.infrastructure.entities.Provider;
import com.tourguide.infrastructure.entities.User;
import com.tourguide.infrastructure.mapper.ProviderMapper;
import com.tourguide.infrastructure.repositories.PricerRepository;
import com.tourguide.infrastructure.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PricerAdapterTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PricerRepository pricerRepository;
    @Mock
    ProviderMapper providerMapper;
    @InjectMocks
    PricerAdapter pricerAdapter;

    @BeforeEach
    public void initMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPrice() {
        User user = User.builder().userName("userName").build();
        com.tourguide.domain.User userDomain = com.tourguide.domain.User.builder().userName("userName").build();
        Provider provider= Provider.builder().name("providerName").build();
        com.tourguide.domain.Provider providerDomain= com.tourguide.domain.Provider.builder().name("providerName").build();
        Mockito.when(userRepository.get(user.getUserName())).thenReturn(Mono.just(user));
        Mockito.when(pricerRepository.getPriceForUser("test-server-api-key", user, 1)).thenReturn(Flux.just(provider));
        Mockito.when(providerMapper.toProviderDomain(provider)).thenReturn(providerDomain);
        StepVerifier.create(pricerAdapter.getPrice(userDomain, 1))
                .expectNext(providerDomain).expectComplete().verify();
    }

}
