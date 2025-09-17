package com.socialanalyzer.analytics.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialAnalyticsDTO {

    private String analyticsId;
    private String postId;
    private String accountId;
    private Long likes;
    private Long comments;
    private Long shares;
    private Long impressions;
    private Long reach;
    private Long saves;
    private Double engagementRate;
    private LocalDateTime lastUpdatedAt;
}
