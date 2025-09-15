package com.social_media_analyzer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@Slf4j
@EnableFeignClients(basePackages = "com.socialanalyzer")
public class SocialMediaAnalyzerUserApplication {

	public static void main(String[] args) {
		try {
			log.info("SocialMediaAnalyzerUserApplication service Starting");
			SpringApplication.run(SocialMediaAnalyzerUserApplication.class, args);
			log.info("SocialMediaAnalyzerUserApplication service started successfully....");
		}catch (Exception e)
		{
			log.error("Error while starting SocialMediaAnalyzerUserApplication service..");
		}
	}

}
