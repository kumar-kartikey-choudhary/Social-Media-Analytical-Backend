package com.socialanalyzer.analytics.service;

import com.socialanalyzer.analytics.dto.SocialAnalyticsDTO;

public interface SocialAnalyticsService {
    SocialAnalyticsDTO syncAnalytics(String postId) throws Exception;

    SocialAnalyticsDTO getAnalytics(String postId) throws Exception;
}
