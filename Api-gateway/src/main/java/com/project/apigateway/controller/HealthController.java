package com.project.apigateway.controller;

import org.jspecify.annotations.NullMarked;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@NullMarked
public class HealthController {

    @GetMapping("health")
    public ResponseEntity<String> testHealth() {
        return ResponseEntity.ok("Config service up");
    }
}
