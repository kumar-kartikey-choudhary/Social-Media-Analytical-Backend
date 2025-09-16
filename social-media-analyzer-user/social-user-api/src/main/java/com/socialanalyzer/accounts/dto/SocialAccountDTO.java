package com.socialanalyzer.accounts.dto;

import com.socialanalyzer.enums.Platform;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SocialAccountDTO {

    private String accountId;
    private String userId;
    private Platform platform;
    private String platformUserId;
    private String platformUsername;
    private String profilePictureUrl;
    private String accountUrl;
    private String accessToken;
    private String refreshToken;
    private boolean isActive;
    private LocalDateTime connectedAt;
    private LocalDateTime lastUpdatedAt;

}
