package com.project.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class ActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityApplication.class, args);
    }

}
