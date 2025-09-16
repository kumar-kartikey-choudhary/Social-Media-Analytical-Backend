package com.socialanalyzer.analytics.service.impl;

import com.socialanalyzer.Util.MapUtils;
import com.socialanalyzer.analytics.dto.SocialAnalyticsDTO;
import com.socialanalyzer.analytics.entity.SocialAnalytics;
import com.socialanalyzer.analytics.repository.SocialAnalyticsRepository;
import com.socialanalyzer.analytics.service.SocialAnalyticsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SocialAnalyticsServiceImpl implements SocialAnalyticsService {

    private final SocialAnalyticsRepository repository;

    @Autowired
    public SocialAnalyticsServiceImpl(SocialAnalyticsRepository repository)
    {
        this.repository = repository;
    }


    @Override
    public SocialAnalyticsDTO syncAnalytics(String postId) {
        return null;
    }

    @Override
    public SocialAnalyticsDTO getAnalytics(String postId) throws Exception {
        log.info("Inside @class SocialAnalyticsServiceImpl @method getAnalytics param :{}", postId);
        if(StringUtils.isBlank(postId))
        {
            throw new IllegalArgumentException("Post Id can not be null or empty");
        }
        SocialAnalytics socialAnalytics = repository.findByPostId(postId).orElseThrow(() -> new RuntimeException("Can not find social analytics"));
        return MapUtils.entityToDto(socialAnalytics, SocialAnalyticsDTO.class);
    }
}
