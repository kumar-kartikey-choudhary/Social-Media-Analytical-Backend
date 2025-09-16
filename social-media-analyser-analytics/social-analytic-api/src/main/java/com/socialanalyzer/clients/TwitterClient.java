package com.socialanalyzer.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "twitter-client" , url = "${social.platform.TWITTER.api-base-url}")
public interface TwitterClient {

    @GetMapping("/users/me")
    Map<String, Object> getUserProfile(@RequestHeader("Authorization") String token);

    @GetMapping("/tweets")
    Map<String, Object> getTweets(
            @RequestHeader("Authorization") String token
//            @RequestParam("ids") String tweetIds
    );

    @GetMapping("/tweets/{id}/metrics")
    Map<String, Object> getTweetMetrics(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") String tweetId
    );
}
