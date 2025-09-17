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
		try {
			log.info("Starting SocialMediaAnalyserAnalyticsApplication service");
			SpringApplication.run(SocialMediaAnalyserAnalyticsApplication.class, args);
			log.info("Started SocialMediaAnalyserAnalyticsApplication service successfully..");
		} catch (Exception e) {
			log.info("Error while Starting SocialMediaAnalyserAnalyticsApplication service..");
		}
	}

}
