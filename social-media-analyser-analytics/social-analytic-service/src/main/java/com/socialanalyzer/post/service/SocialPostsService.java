package com.socialanalyzer.post.service;

import com.socialanalyzer.post.dto.SocialPostDTO;

import java.util.List;

public interface SocialPostsService {

    List<SocialPostDTO> syncPosts(String accountId);

    List<SocialPostDTO> getPosts(String accountId);

    SocialPostDTO save(SocialPostDTO socialPostDTO);
}
