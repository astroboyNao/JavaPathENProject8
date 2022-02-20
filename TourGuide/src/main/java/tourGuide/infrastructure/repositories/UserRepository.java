package tourguide.infrastructure.repositories;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import tourguide.infrastructure.entities.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type User repository.
 */
@Repository
public class UserRepository {

    // Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
    private final Map<String, User> internalUserMap = new ConcurrentHashMap<>();

    /**
     * Add.
     *
     * @param userName the user name
     * @param user     the user
     */
    public void add(String userName, User user) {
        internalUserMap.put(userName, user);
    }

    /**
     * Get mono.
     *
     * @param userName the user name
     * @return the mono
     */
    public Mono<User> get(String userName) {
        return Mono.just(internalUserMap.get(userName));
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<User> findAll() {
        return List.copyOf(internalUserMap.values());
    }

    /**
     * Save mono.
     *
     * @param user the user
     * @return the mono
     */
    public Mono<Void> save(User user) {
        internalUserMap.put(user.getUserName(), user);
        return Mono.empty();
    }
}
