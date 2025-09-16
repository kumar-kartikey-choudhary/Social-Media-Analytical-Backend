package com.socialanalyzer.analytics.controller;

import com.socialanalyzer.analytics.dto.SocialAnalyticsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@FeignClient(name = "social-analytics-service")
public interface SocialAnalyticsController {

    // Sync analytics from platform
    @PostMapping(path = "/posts/{postId}/analytics/sync")
    ResponseEntity<SocialAnalyticsDTO>  syncAnalytics(@PathVariable(name = "postId") String postId);


    @GetMapping(path = "/posts/{postId}/analytics")
    ResponseEntity<SocialAnalyticsDTO> getAnalytics(@PathVariable(name = "postId") String postId);




}
