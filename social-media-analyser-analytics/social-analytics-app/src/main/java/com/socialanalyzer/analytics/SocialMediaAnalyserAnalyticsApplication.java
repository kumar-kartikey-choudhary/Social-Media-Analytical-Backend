package com.socialanalyzer.analytics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.socialanalyzer")
@Slf4j
public class SocialMediaAnalyserAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaAnalyserAnalyticsApplication.class, args);
	}

}
