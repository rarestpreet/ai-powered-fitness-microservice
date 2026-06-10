package com.project.aimodel.data;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@NullMarked
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
    Optional<Recommendation> findByActivityId(String activityId);

    List<Recommendation> findByUserId(String userId);
}
