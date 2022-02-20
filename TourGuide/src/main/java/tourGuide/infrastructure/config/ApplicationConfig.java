package tourguide.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The type Application config.
 */
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
