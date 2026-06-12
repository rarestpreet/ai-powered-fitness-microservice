package com.project.activity.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    @LoadBalanced
    public RestClient.Builder webClient() {
        return RestClient.builder();
    }

    @Bean
    public RestClient userServiceWebClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://USER")
                .build();
    }
}
