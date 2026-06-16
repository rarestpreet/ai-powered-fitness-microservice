package com.project.aimodel.service;

import com.project.aimodel.model.Activity;
import com.project.aimodel.model.Recommendation;
import com.project.aimodel.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService aiService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics = "activity", groupId = "recommendation-group")
    public void processActivity(Activity activity) {
        log.info("Received activity for processing: {}", activity.getId());

        Recommendation recommendation = aiService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }
}
