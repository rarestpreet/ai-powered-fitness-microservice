package com.project.aimodel.controller;

import com.project.aimodel.data.Activity;
import com.project.aimodel.data.Recommendation;
import com.project.aimodel.data.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
@NullMarked
public class RecommendationController {

    private final RecommendationRepository recommendationRepo;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId) {
        List<Recommendation> response = recommendationRepo.findByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId) {
        Recommendation response = recommendationRepo.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("No recommendation found for this activity: " + activityId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/activity")
    @KafkaListener(topics = "activity")
    public ResponseEntity<String> checkMessage(@RequestParam Activity activity) {
        return ResponseEntity.ok(activity.getUserId());
    }
}
