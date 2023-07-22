package com.bhagwati.ContractManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Cors config.
 *
 * @author Akash Thomas.
 */
@Configuration
public class CorsConfig {

    /**
     * Cors web filter cors web filter.
     *
     * @return the cors web filter
     */
    @Bean
    CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        List<String> corsOrgins = new ArrayList<>();
        corsOrgins.add("http://localhost:4200");
        corsConfig.setAllowedOriginPatterns(corsOrgins);
        corsConfig.setAllowCredentials(false);
        corsConfig.addAllowedMethod("OPTIONS");
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("PUT");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("DELETE");
        corsConfig.addAllowedMethod("PATCH");
        corsConfig.addAllowedHeader("*");
        corsConfig.setExposedHeaders(Arrays.asList(HttpHeaders.SET_COOKIE, HttpHeaders.ETAG, HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
