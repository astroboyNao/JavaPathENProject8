package com.tourguide.infrastructure.repositories;

import com.tourguide.infrastructure.entities.User;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserRepository {
    void add(String userName, User user);

    Mono<User> get(String userName);

    List<User> findAll();

    Mono<Void> save(User user);
}
