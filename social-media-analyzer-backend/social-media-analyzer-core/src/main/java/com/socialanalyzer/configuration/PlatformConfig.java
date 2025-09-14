package com.socialanalyzer.configuration;


import com.socialanalyzer.enums.Platform;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "social.platforms") // Binds to properties starts with social.platforms
@Data
public class PlatformConfig {

    private Map<Platform , PlatformDetails> details = new EnumMap<>(Platform.class);

    public PlatformDetails getDetails(Platform platform)
    {
        return details.get(platform);
    }

    @Data
    public static class PlatformDetails
    {
        private String clientId;
        private String clientSecret;
        private String authUrl;
        private String tokenUrl;
        private String redirectUrl; // This might be dynamically generated or configured per environment
        private String apiBaseUrl;
        private String scope;  // Can be a comma-separated string, e.g., "public_profile,email"
        private String profileApiUrl; // Specific API endpoint to fetch user profile
    }

}
