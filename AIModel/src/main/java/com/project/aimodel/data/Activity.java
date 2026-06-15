package com.project.aimodel.data;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Activity {
    private String id;
    private String userId;
    private String type;
    private Long duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
}

