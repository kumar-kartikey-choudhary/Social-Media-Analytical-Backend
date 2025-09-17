package com.socialanalyzer.post.service.impl;

import com.socialanalyzer.Util.MapUtils;
import com.socialanalyzer.accounts.dto.SocialAccountDTO;
import com.socialanalyzer.clients.InstagramClient;
import com.socialanalyzer.post.dto.SocialPostDTO;
import com.socialanalyzer.post.entity.SocialPost;
import com.socialanalyzer.post.repository.SocialPostRepository;
import com.socialanalyzer.post.service.SocialPostsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.socialanalyzer.accounts.controller.*;

import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class SocialPostsServiceImpl implements SocialPostsService {

    private final SocialPostRepository repository;
    private final InstagramClient client;
    private final SocialAccountController controller;




    @Autowired
    public SocialPostsServiceImpl(SocialPostRepository repository,InstagramClient client, SocialAccountController controller)
    {
        this.repository = repository;
        this.client = client;
        this.controller =controller;

    }



    @Override
    public SocialPostDTO save(SocialPostDTO socialPostDTO) {
        log.info("Inside @class SocialPostServiceImpl @method save :{}",socialPostDTO);
        try {
            Objects.requireNonNull(socialPostDTO, "Social Post object must not be null");
            if(StringUtils.isNotBlank(socialPostDTO.getPostId()))
            {
                throw new IllegalCallerException("Social Post id must be null");
            }
            SocialPost socialPost = MapUtils.dtoToEntity(socialPostDTO, SocialPost.class);
            socialPost = repository.saveAndFlush(socialPost);

            return MapUtils.entityToDto(socialPost, SocialPostDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Something went wrong...");
        }
    }

    @Override
    public List<SocialPostDTO> syncPosts(String accountId) {
        log.info("Inside @class SocialPostServiceImpl @method syncPosts @Param accountId:{}",accountId);
        if(StringUtils.isBlank(accountId))
        {
            throw new IllegalArgumentException("Account id can not be null or empty");
        }
        try
        {
            ResponseEntity<SocialAccountDTO> socialAccountDto = controller.findByAccountId(accountId);
            SocialAccountDTO account = socialAccountDto.getBody();
            if (account == null || StringUtils.isBlank(account.getAccessToken())) {
                throw new RuntimeException("No valid access token found for accountId: " + accountId);
            }

            List<SocialPostDTO> posts = client.getUserPosts(account.getAccessToken());
            if (posts == null || posts.isEmpty()) {
                log.info("No posts found from Instagram for accountId: {}", accountId);
                return List.of();
            }

            posts.forEach(post-> {
                try {
                    SocialPost socialPost = MapUtils.dtoToEntity(post, SocialPost.class);
                    socialPost.setAccountId(accountId);
                    repository.saveAndFlush(socialPost);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });

            return posts;
        } catch (Exception e) {
            throw new RuntimeException("SOMETHING-WENT-WRONG");
        }

    }

    @Override
    public List<SocialPostDTO> getPosts(String accountId) {
        log.info("Inside @class SocialPostServiceImpl @method getPosts @Param accountId:{}",accountId);
        if(StringUtils.isBlank(accountId))
        {
            throw new IllegalArgumentException("Account id can not be null or empty");
        }
        try
        {
            List<SocialPost> socialPosts = repository.findByAccountId(accountId);
            return  socialPosts.stream().map(socialPost -> {
                try {
                    return MapUtils.entityToDto(socialPost, SocialPostDTO.class);
                } catch (Exception e) {
                    throw new RuntimeException("MAPPING_FAILED");
                }
            }).toList();
        } catch (Exception e) {
            throw new RuntimeException("SOMETHING-WENT-WRONG");
        }
    }

}
