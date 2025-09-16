package com.socialanalyzer.post.repository;

import com.socialanalyzer.post.entity.SocialPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialPostRepository extends JpaRepository<SocialPost, String> {

    List<SocialPost> findByAccountId(String accountId);
    SocialPost findByPlatformPostId(String platformPostId);
}
