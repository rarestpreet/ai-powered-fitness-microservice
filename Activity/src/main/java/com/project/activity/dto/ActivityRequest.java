package com.project.activity.dto;

import com.project.activity.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Long duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
}
