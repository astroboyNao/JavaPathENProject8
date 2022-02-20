package tourguide.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import tourguide.domain.Provider;
import tourguide.domain.User;
import tourguide.domain.service.Pricer;
import tourguide.infrastructure.repositories.PricerRepository;
import tourguide.infrastructure.repositories.UserRepository;

@Service
@AllArgsConstructor
public class PricerService implements Pricer {
    private static final String apiKey = "test-server-api-key";
    private UserRepository userRepository;
    private PricerRepository pricerRepository;

    @Override
    public Flux<Provider> getPrice(User user, int cumulatativeRewardPoints) {
        return userRepository.get(user.getUserName()).flatMapMany(
                userEntities -> pricerRepository.getPriceForUser(apiKey, userEntities, cumulatativeRewardPoints)
        );
    }

}
