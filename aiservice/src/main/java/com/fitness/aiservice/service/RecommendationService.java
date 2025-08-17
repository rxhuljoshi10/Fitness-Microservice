package com.fitness.aiservice.service;

import com.fitness.aiservice.Repo.RecommendationsRepo;
import com.fitness.aiservice.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {
    @Autowired
    private  RecommendationsRepo recommendationsRepo;

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationsRepo.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationsRepo.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("No recommendation found for this activity "));
    }
}
