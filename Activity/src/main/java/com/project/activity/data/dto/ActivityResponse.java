package com.project.activity.data.dto;

import com.project.activity.data.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@Builder
public class ActivityResponse {
    private String userId;
    private ActivityType type;
    private Long duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
}