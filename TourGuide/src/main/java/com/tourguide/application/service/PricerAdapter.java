package com.tourguide.application.service;

import com.tourguide.domain.Provider;
import com.tourguide.domain.User;
import com.tourguide.domain.service.Pricer;
import com.tourguide.infrastructure.mapper.ProviderMapper;
import com.tourguide.infrastructure.mapper.UserMapper;
import com.tourguide.infrastructure.repositories.PricerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.tourguide.infrastructure.repositories.UserRepository;

@Service
@AllArgsConstructor
public class PricerAdapter implements Pricer {
    private static final String apiKey = "test-server-api-key";
    private ProviderMapper providerMapper;
    private UserRepository userRepository;
    private PricerRepository pricerRepository;

    @Override
    public Flux<Provider> getPrice(User user, int cumulatativeRewardPoints) {
        return userRepository.get(user.getUserName()).flatMapMany(
                userEntities -> pricerRepository.getPriceForUser(apiKey, userEntities, cumulatativeRewardPoints)
        ).map(provider -> providerMapper.toProviderDomain(provider));
    }

}
