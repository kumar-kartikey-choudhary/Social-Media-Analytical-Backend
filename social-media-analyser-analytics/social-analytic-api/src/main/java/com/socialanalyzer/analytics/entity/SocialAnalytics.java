package com.socialanalyzer.analytics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class SocialAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ANALYTICS_ID", columnDefinition = "CHAR(50) NOT NULL")
    private String analyticsId;

    @NotNull
    @Column(name = "POST_ID", columnDefinition = "CHAR(50) NOT NULL")
    private String postId;

    @Column(name = "LIKES")
    private Long likes;

    @Column(name = "COMMENTS")
    private Long comments;

    @Column(name = "SHARES")
    private Long shares;

    @Column(name = "IMPRESSIONS")
    private Long impressions;

    @Column(name = "REACH")
    private Long reach;

    @Column(name = "SAVES")
    private Long saves;

    @Column(name = "ENGAGEMENT_RATE")
    private Double engagementRate;

    @UpdateTimestamp
    @Column(name = "LAST_UPDATED_AT")
    private LocalDateTime lastUpdatedAt;
}
