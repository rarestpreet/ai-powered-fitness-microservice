package com.project.aimodel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClient() {
        return RestClient.builder();
    }

    @Bean
    public RestClient userServiceRestClient(RestClient.Builder builder) {
        return builder
                .build();
    }
}
