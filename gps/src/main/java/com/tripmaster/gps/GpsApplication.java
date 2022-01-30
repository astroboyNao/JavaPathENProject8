package com.tripmaster.gps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableWebFlux
public class GpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpsApplication.class, args);
    }

}
