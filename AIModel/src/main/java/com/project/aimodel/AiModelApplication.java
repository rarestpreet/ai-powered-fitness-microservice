package com.project.aimodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class AiModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiModelApplication.class, args);
    }

}
