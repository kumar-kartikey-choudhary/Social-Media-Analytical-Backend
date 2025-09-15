package com.socialanalyzer.social.model;


import com.socialanalyzer.enums.Platform;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "userId" , "platform"})
        })
public class SocialAccount {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "SOCIAL_ACCOUNT_ID", columnDefinition = "CHAR(50) NOT NULL")
    private String accountId;

    @NotNull
    @Column(name = "USER_ID" , columnDefinition = "CHAR(50) NOT NULL" , updatable = false)
    private String userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "PLATFORM", columnDefinition = "VARCHAR(20) NOT NULL" )
    private Platform platform;


    @Column(name = "PLATFORM_USER_ID" , columnDefinition = "VARCHAR(100) NOT NULL" , length = 100)
    @NotNull
    private String platformUserId;

    @NotNull
    @Column(name = "PLATFORM_USERNAME" , columnDefinition = "VARCHAR(100) NOT NULL")
    private String platformUsername;


    @Column(name = "PROFILE_PICTURE_URL", length = 500)
    private String profilePictureUrl;


    @NotNull
    @Column(name = "ACCOUNT_URL" , columnDefinition = "VARCHAR(500) NOT NULL" , length = 500)
    private String accountUrl;

    @NotNull
    @Column(name = "ACCESS_TOKEN", columnDefinition = "VARCHAR(1024) NOT NULL" , length = 1024)
    private String accessToken;

    @NotNull
    @Column(name = "REFRESH_TOKEN", columnDefinition = "VARCHAR(1024) NOT NULL" , length = 1024)
    private String refreshToken;

    @NotNull
    @Column(name = "IS_ACTIVE" , columnDefinition = "TINYINT(1) NOT NULL DEFAULT '1'")
    private boolean isActive;

    @CurrentTimestamp
    @Column(name = "CONNECTED_AT")
    private LocalDateTime connectedAt;

    @UpdateTimestamp
    @Column(name = "LAST_UPDATED_AT")
    private LocalDateTime lastUpdatedAt;



}
