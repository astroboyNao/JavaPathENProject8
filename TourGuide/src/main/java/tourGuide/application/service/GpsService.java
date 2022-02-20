package tourguide.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tourguide.domain.Attraction;
import tourguide.domain.User;
import tourguide.domain.VisitedLocation;
import tourguide.domain.service.Gps;
import tourguide.infrastructure.mapper.AttractionMapper;
import tourguide.infrastructure.repositories.GpsRepository;
import tourguide.infrastructure.repositories.UserRepository;

@Service
@AllArgsConstructor
public class GpsService implements Gps {
    private UserRepository userRepository;
    private GpsRepository gpsRepository;
    private AttractionMapper attractionMapper;

    @Override
    public Mono<VisitedLocation> getUserLocation(User user) {
        return userRepository.get(user.getUserName()).flatMap(
                userEntities -> gpsRepository.getVisitedLocationForUser(userEntities.getUserId())
        );
    }

    @Override
    public Flux<Attraction> getAttractions() {
        return gpsRepository.getAttractions().map(
                attraction -> attractionMapper.toAttractionDomain(attraction)
        );
    }

}
