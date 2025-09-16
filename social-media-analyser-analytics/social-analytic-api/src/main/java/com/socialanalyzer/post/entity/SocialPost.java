package com.socialanalyzer.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class SocialPost {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "POST_ID" , columnDefinition = "CHAR(50) NOT NULL DEFAULT(UUID())")
    private String postId;

    @NotNull
    @Column(name = "ACCOUNT_ID" , columnDefinition = "VARCHAR(50) NOT NULL ")
    private String accountId;

    @NotNull
    @Column(name = "PLATFORM_POST_ID", columnDefinition = "VARCHAR(100) NOT NULL ")
    private String platformPostId;

    @NotNull
    @Column(name = "CONTENT_TYPE", columnDefinition = "VARCHAR(20) NOT NULL")
    private String contentType;

    @Column(name = "CAPTION", length = 5000)
    private String caption;

    @Column(name = "MEDIA_URL", length = 1000)
    private String mediaUrl;

    @CurrentTimestamp
    @Column(name = "POSTED_AT")
    private LocalDateTime postedAt;

    @UpdateTimestamp
    @Column(name = "LAST_UPDATED_AT")
    private LocalDateTime lastPostedAt;
}
