package com.tourguide.infrastructure;

import com.tourguide.application.Application;
import com.tourguide.infrastructure.config.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration(classes = {Application.class, ApplicationConfig.class})
@TestPropertySource("classpath:applicationTest.properties")
public class ApplicationTestConfig {
   // @Test
    void contextLoads() {
    }
}
