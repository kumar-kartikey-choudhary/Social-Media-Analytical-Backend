package com.socialanalyzer.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "instagram-client", url = "${social.platform.INSTAGRAM.api-base-url}")
public interface InstagramClient {

    @GetMapping(path = "/me/media")
    Map<String, Object> getUserPosts(@RequestParam(name = "access_token") String accessToken);

    @GetMapping(path = "/{mediaId}/insights")
    Map<String, Object> getPostsInsights(
            @PathVariable(name = "mediaId") String mediaId,
            @RequestParam(name = "access_token") String accessToken
    );

}
