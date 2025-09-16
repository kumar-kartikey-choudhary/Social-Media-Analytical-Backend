package com.socialanalyzer.post.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SocialPostDTO {

    private String postId;
    private String accountId;
    private String platformPostId;
    private String contentType;
    private String caption;
    private String mediaUrl;
    private LocalDateTime postedAt;
    private LocalDateTime lastPostedAt;
}
