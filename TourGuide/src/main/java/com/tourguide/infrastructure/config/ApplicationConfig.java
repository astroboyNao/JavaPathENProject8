package com.tourguide.infrastructure.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * The type Application config.
 */
@Configuration
@Log
@EnableWebFlux
public class ApplicationConfig {

    /**
     * The constant USER.
     */
    public static final String USER = "service";
    /**
     * The constant PASSWORD.
     */
    public static final String PASSWORD = "pwd";

    /**
     * Gets reward central client.
     *
     * @param baseUrl the base url
     * @return the reward central client
     */
    @Bean(name = "reward-central-client")
    public WebClient getRewardCentralClient(@Value("${reward-central-baseurl}") String baseUrl) {
        HttpClient httpClient = HttpClient.create().tcpConfiguration(tcpClient ->
                tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,100).doOnConnected(
                        connection -> connection.addHandlerLast(new ReadTimeoutHandler(500, TimeUnit.MILLISECONDS))
                ));
        return WebClient.builder().baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(ExchangeFilterFunctions.basicAuthentication(USER, PASSWORD))
                .build();
    }

    /**
     * Gets pricer client.
     *
     * @param baseUrl the base url
     * @return the pricer client
     */
    @Bean(name = "pricer-client")
    public WebClient getPricerClient(@Value("${pricer-baseurl}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(ExchangeFilterFunctions.basicAuthentication(USER, PASSWORD))
                .build();
    }

    /**
     * Gets gps client.
     *
     * @param baseUrl the base url
     * @return the gps client
     */
    @Bean(name = "gps-client")
    public WebClient getGpsClient(@Value("${gps-baseurl}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(ExchangeFilterFunctions.basicAuthentication(USER, PASSWORD))
                .build();
    }


}
