package com.socialanalyzer.social.repository;

import com.socialanalyzer.enums.Platform;
import com.socialanalyzer.social.model.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocialAccountRepository extends JpaRepository<SocialAccount , String> {

    Optional<SocialAccount> getByUserIdAndPlatform(String userId, Platform platform);
    Optional<SocialAccount> getByPlatformAndPlatformUserId(Platform platform , String platformUserId);
    List<SocialAccount> getByUserId(String userId);
}
