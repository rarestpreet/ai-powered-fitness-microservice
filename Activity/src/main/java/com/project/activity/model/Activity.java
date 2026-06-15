package com.project.activity.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import com.project.activity.data.ActivityType;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Activity {
    private String id;
    private String userId;
    private ActivityType type;
    private Long duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
}
