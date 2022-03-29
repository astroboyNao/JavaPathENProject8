package com.tourguide.infrastructure.repositories;

import com.tourguide.infrastructure.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type User repository.
 */
@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    // Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
    private final Map<String, User> internalUserMap = new ConcurrentHashMap<>();

    /**
     * Add.
     *
     * @param userName the user name
     * @param user     the user
     */
    @Override
    public void add(String userName, User user) {
        internalUserMap.put(userName, user);
    }

    /**
     * Get mono.
     *
     * @param userName the user name
     * @return the mono
     */
    @Override
    public Mono<User> get(String userName) {
        return Mono.just(internalUserMap.get(userName));
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @Override
    public List<User> findAll() {
        return List.copyOf(internalUserMap.values());
    }

    /**
     * Save mono.
     *
     * @param user the user
     * @return the mono
     */
    @Override
    public Mono<Void> save(User user) {
        internalUserMap.put(user.getUserName(), user);
        return Mono.empty();
    }
}
