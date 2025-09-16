package com.socialanalyzer.clients.impl;

import com.socialanalyzer.Util.MapUtils;
import com.socialanalyzer.clients.InstagramClient;
import com.socialanalyzer.clients.TwitterClient;
import com.socialanalyzer.post.dto.SocialPostDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SocialClientsImpl {

    private final InstagramClient instagramClient;
    private final TwitterClient twitterClient;

    public Map<String,Object> fetchInstagramPosts(String accessToken) throws Exception {
        return instagramClient.getUserPosts(accessToken);
    }

    public Map<String,Object> fetchTwitterPosts(String accessToken) throws Exception {
        return twitterClient.getTweets(accessToken);
    }
}
