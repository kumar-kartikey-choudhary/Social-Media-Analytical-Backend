package com.socialanalyzer.analytics.repository;

import com.socialanalyzer.analytics.entity.SocialAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialAnalyticsRepository extends JpaRepository<SocialAnalytics ,String> {

    Optional<SocialAnalytics> findByPostId(String postId);
 }
