package com.project.activity.controller;

import org.jspecify.annotations.NullMarked;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@NullMarked
public class HealthCheck {

    @GetMapping
    public ResponseEntity<String> testHealth() {
        return ResponseEntity.ok("Activity service up");
    }
}
