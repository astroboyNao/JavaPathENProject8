package com.tourguide.application;

import com.tourguide.domain.*;
import com.tourguide.domain.service.Gps;
import com.tourguide.domain.service.Pricer;
import com.tourguide.domain.service.RewardsCalculator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Application.
 */
@SpringBootApplication
@EnableWebFlux
@ComponentScan(basePackages = "com.tourguide")
public class Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public TourGuide getGpsClient(Gps gps, Pricer pricer, RewardsCalculator rewardsCalculator) {
        return new TourGuideImpl(gps, pricer, rewardsCalculator);
    }
}
