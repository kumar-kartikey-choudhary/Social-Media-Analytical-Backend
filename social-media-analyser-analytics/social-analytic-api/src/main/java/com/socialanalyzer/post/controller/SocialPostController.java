package com.socialanalyzer.post.controller;

import com.socialanalyzer.post.dto.SocialPostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ResponseBody
@FeignClient(name = "social-post-service",url = "${post.url}")
public interface SocialPostController {

    @PostMapping(path = "/save")
    ResponseEntity<SocialPostDTO> save(@RequestBody SocialPostDTO socialPostDTO);

    @PostMapping(path = "/accounts/{accountId}/posts/sync")
    ResponseEntity<List<SocialPostDTO>> syncPosts(@PathVariable(name = "accountId") String accountId);


    @GetMapping(path = "/accounts/{accountId}/posts")
    ResponseEntity<List<SocialPostDTO>> getPosts(@PathVariable(name = "accountId") String accountId);
}
