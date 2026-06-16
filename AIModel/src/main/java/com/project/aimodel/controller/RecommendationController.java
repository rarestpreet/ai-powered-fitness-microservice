package com.project.aimodel.controller;

import com.project.aimodel.model.Recommendation;
import com.project.aimodel.repository.RecommendationRepository;
import com.project.aimodel.service.ActivityAIService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@NullMarked
public class RecommendationController {

    private final RecommendationRepository recommendationRepo;
    private final ActivityAIService activityAIService;

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId) {
        List<Recommendation> response = recommendationRepo.findByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId) {
        Recommendation response = recommendationRepo.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("No recommendation found for this activity: " + activityId));
        return ResponseEntity.ok(response);
    }
}
