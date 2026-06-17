package com.project.activity.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestClientService {

    private final RestClient builder;

    public RestClientService(@Qualifier("loadBalancedBuilder") RestClient.Builder loadBalancedBuilder) {
        this.builder = loadBalancedBuilder.build();
    }

    public boolean validateUser(String userId) {
        return Boolean.TRUE.equals(
                builder
                        .get()
                        .uri("http://USER-SERVICE/user/api/{userId}/validate", userId)
                        .retrieve()
                        .body(Boolean.class)
        );
    }
}
