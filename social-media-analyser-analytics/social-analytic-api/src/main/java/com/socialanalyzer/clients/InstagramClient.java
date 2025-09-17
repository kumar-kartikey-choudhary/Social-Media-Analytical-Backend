package com.socialanalyzer.clients;


import com.socialanalyzer.post.dto.SocialPostDTO;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "instagram-client", url = "${social.platform.INSTAGRAM.api-base-url}")
public interface InstagramClient {

    @GetMapping(path = "/me/media")
    List<SocialPostDTO> getUserPosts(@RequestParam(name = "access_token") String accessToken);

    @GetMapping(path = "/{mediaId}/postInsights")
    Map<String, Object> getPostsInsights(
            @PathVariable(name = "mediaId") String mediaId,
            @RequestParam(name = "access_token") String accessToken
    );

    @GetMapping(path = "/{mediaId}/mediaInsights")
    Map<String ,Object> getMediaInsights(
            @PathVariable("mediaId") String mediaId,
            @RequestParam("access_token") String accessToken
    );

}
