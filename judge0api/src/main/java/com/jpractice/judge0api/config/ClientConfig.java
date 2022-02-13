package com.jpractice.judge0api.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Artem Anosov
 */
@Configuration
public class ClientConfig {

    private static final String CONTENT_TYPE = "application/json";
    private static final String X_RAPIDAPI_HOST = "judge0-ce.p.rapidapi.com";
    private static final String X_RAPIDAPI_KEY = "aef6e0e391mshacbc9b668a210b3p13a25ejsna71dba04aa7c";

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("content-type", CONTENT_TYPE);
            requestTemplate.header("x-rapidapi-host", X_RAPIDAPI_HOST);
            requestTemplate.header("x-rapidapi-key", X_RAPIDAPI_KEY);
        };
    }
}
