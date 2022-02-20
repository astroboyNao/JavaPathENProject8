package tourguide.domain.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;
import tourguide.domain.Attraction;
import tourguide.domain.Reward;
import tourguide.domain.User;
import tourguide.domain.VisitedLocation;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The interface Rewards calculator.
 */
@Service
public interface RewardsCalculator {

	/**
	 * Calculate rewards mono.
	 *
	 * @param user        the user
	 * @param attractions the attractions
	 * @return the mono
	 */
	default Mono<User> calculateRewards(User user, List<Attraction> attractions) {
        var nearAttractions = getNearAttractions(user, attractions);
        return Flux.fromStream(nearAttractions)
                .flatMap(simpleEntry -> this.getRewardPoints(simpleEntry.getKey(), user).map(rewardPoints -> Tuples.of(simpleEntry, rewardPoints)))
                .map(tuples -> Reward.builder().visitedLocation(tuples.getT1().getValue()).attraction(tuples.getT1().getKey()).rewardPoints(tuples.getT2()).build())
                .collectList()
                .map(rewards -> user.toBuilder().rewards(rewards).build());
    }

    private Stream<AbstractMap.SimpleEntry<Attraction, VisitedLocation>> getNearAttractions(User user, List<Attraction> attractions) {
        List<Attraction> newAttractions = getNewAttraction(user, attractions);
        return user.getVisitedLocations().stream().map(visitedLocation -> visitedLocation.nearAttraction(newAttractions)).flatMap(Set::stream);
    }

    private List<Attraction> getNewAttraction(User user, List<Attraction> attractions) {
        Map<String, Attraction> userRewards = getAttractionMap(user);
        var newAttractions = attractions.stream().filter(attraction -> !userRewards.containsKey(attraction.getAttractionName())).collect(Collectors.toList());
        return newAttractions;
    }

    private Map<String, Attraction> getAttractionMap(User user) {
        var userRewards = user.getRewards().stream().collect(Collectors.toMap(k -> k.getAttraction().getAttractionName(), v -> v.getAttraction()));
        return userRewards;
    }


	/**
	 * Gets reward points.
	 *
	 * @param attraction the attraction
	 * @param user       the user
	 * @return the reward points
	 */
	Mono<Integer> getRewardPoints(Attraction attraction, User user);
}
