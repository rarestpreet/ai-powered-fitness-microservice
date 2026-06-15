package com.project.user.controller;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@NullMarked
@RequiredArgsConstructor
@RefreshScope
public class HealthCheck {

    @Value("${test.value}")
    private String testValue;

    @GetMapping("/health")
    public ResponseEntity<String> testHealth() {
        return ResponseEntity.ok("User service up");
    }

    @GetMapping("/config")
    public ResponseEntity<String> testConfig() {
        return ResponseEntity.ok(testValue);
    }
}
