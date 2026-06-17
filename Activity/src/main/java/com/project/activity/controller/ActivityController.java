package com.project.activity.controller;

import com.project.activity.dto.ActivityRequest;
import com.project.activity.dto.ActivityResponse;
import com.project.activity.model.Activity;
import com.project.activity.repository.ActivityRepository;
import com.project.activity.service.RestClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@NullMarked
@Slf4j
public class ActivityController {

    private final ActivityRepository activityRepo;
    private final KafkaTemplate<String, Activity> kafkaTemplate;
    private final RestClientService restClientService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request) {
        boolean isValidUser = validateUser(request.getUserId());
        if (!isValidUser) {
            throw new RuntimeException("Invalid User: " + request.getUserId());
        }

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .build();

        Activity savedActivity = activityRepo.save(activity);

        try {
            kafkaTemplate.send("activity", activity);
        } catch (KafkaException e) {
            log.error("Error while sending activity to Kafka Client {}", e.getMessage());
            throw new KafkaException("Error while sending activity to Kafka Client");
        }
        ActivityResponse response = ActivityResponse.builder()
                .userId(activity.getUserId())
                .type(activity.getType())
                .caloriesBurned(activity.getCaloriesBurned())
                .startTime(activity.getStartTime())
                .duration(activity.getDuration())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@PathVariable String userId) {
        List<Activity> activities = activityRepo.findByUserId(userId);

        List<ActivityResponse> response = activities.stream()
                .map(activity ->
                        ActivityResponse.builder()
                                .userId(activity.getUserId())
                                .type(activity.getType())
                                .caloriesBurned(activity.getCaloriesBurned())
                                .startTime(activity.getStartTime())
                                .duration(activity.getDuration())
                                .build()
                )
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{activityId}")
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable String activityId) {
        ActivityResponse response = activityRepo.findById(activityId)
                .map(activity ->
                        ActivityResponse.builder()
                                .userId(activity.getUserId())
                                .type(activity.getType())
                                .caloriesBurned(activity.getCaloriesBurned())
                                .startTime(activity.getStartTime())
                                .duration(activity.getDuration())
                                .build()
                )
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + activityId));

        return ResponseEntity.ok(response);
    }

    @GetMapping("validate-user/{userId}")
    public boolean validateUser(@PathVariable String userId) {
        log.info("Calling User Validation API for userId: {}", userId);
        try {
            return restClientService.validateUser(userId);
        } catch (RestClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new RuntimeException("User Not Found: " + userId);
            else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                throw new RuntimeException("Invalid Request: " + userId);
            else
                throw new RuntimeException("Unexpected Error: " + e.getResponseBodyAsString());
        }
    }

}