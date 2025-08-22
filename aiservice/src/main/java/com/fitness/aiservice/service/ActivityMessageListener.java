package com.fitness.aiservice.service;

import com.fitness.aiservice.Repo.RecommendationsRepo;
import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAiService aiService;
    @Autowired
    private  RecommendationsRepo recommendationsRepo;

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity) {
        log.info("Received activity for processing: {}", activity.getId());
        Recommendation recommendation = aiService.generateRecommendation(activity);
        recommendationsRepo.save(recommendation);
    }
}
