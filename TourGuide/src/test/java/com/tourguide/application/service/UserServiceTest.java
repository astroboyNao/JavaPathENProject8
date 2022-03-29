package com.tourguide.application.service;

import com.tourguide.infrastructure.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class UserServiceTest {
    UserRepository userRepository;
    UserService service;

    @BeforeEach
    public void initMock() {
        userRepository = Mockito.mock(UserRepository.class);
        service = new UserService(true, 100, userRepository);
    }

    @Test
    public void initTest() {

        service.init();
        Mockito.verify(userRepository, Mockito.times(100)).add(Mockito.anyString(), Mockito.any());

    }

}
