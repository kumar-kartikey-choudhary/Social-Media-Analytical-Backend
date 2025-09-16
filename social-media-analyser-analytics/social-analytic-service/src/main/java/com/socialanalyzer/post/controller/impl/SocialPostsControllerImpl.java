package com.socialanalyzer.post.controller.impl;

import com.socialanalyzer.post.controller.SocialPostController;
import com.socialanalyzer.post.dto.SocialPostDTO;
import com.socialanalyzer.post.service.SocialPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class SocialPostsControllerImpl implements SocialPostController {

    private final SocialPostsService service;

    @Autowired
    public SocialPostsControllerImpl(SocialPostsService service)
    {
        this.service = service;
    }

    @Override
    public ResponseEntity<SocialPostDTO> save(SocialPostDTO socialPostDTO) {
        return ResponseEntity.ok(this.service.save(socialPostDTO));
    }

    @Override
    public ResponseEntity<List<SocialPostDTO>> syncPosts(String accountId) {
        return ResponseEntity.ok(this.service.syncPosts(accountId));
    }

    @Override
    public ResponseEntity<List<SocialPostDTO>> getPosts(String accountId) {
        return ResponseEntity.ok(this.service.getPosts(accountId));
    }
}
