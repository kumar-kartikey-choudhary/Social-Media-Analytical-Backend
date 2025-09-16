package com.socialanalyzer.analytics.controller.impl;

import com.socialanalyzer.analytics.controller.SocialAnalyticsController;
import com.socialanalyzer.analytics.dto.SocialAnalyticsDTO;
import com.socialanalyzer.analytics.repository.SocialAnalyticsRepository;
import com.socialanalyzer.analytics.service.SocialAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class SocialAnalyticsControllerImpl implements SocialAnalyticsController {

    private final SocialAnalyticsService service;

    @Autowired
    public SocialAnalyticsControllerImpl(SocialAnalyticsService service)
    {
        this.service = service;
    }

    @Override
    public ResponseEntity<SocialAnalyticsDTO> syncAnalytics(String postId) {
        return ResponseEntity.ok(this.service.syncAnalytics(postId));
    }

    @Override
    public ResponseEntity<SocialAnalyticsDTO> getAnalytics(String postId) {
        return ResponseEntity.ok(this.service.getAnalytics(postId));
    }
}
