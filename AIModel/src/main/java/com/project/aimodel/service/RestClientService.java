package com.project.aimodel.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class RestClientService {

    private final RestClient builder;
    @Value("${custom.gemini.url}")
    private String geminiApiUrl;
    @Value("${custom.gemini.api-key}")
    private String geminiApiKey;

    public RestClientService(@Qualifier("defaultBuilder") RestClient.Builder loadBalancedBuilder) {
        this.builder = loadBalancedBuilder.build();
    }

    public String ai_response(Map<String, Object> requestBody) {
        return builder.post()
                .uri(geminiApiUrl + geminiApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(String.class);
    }
}
