package com.socialanalyzer.analytics.service.impl;

import com.socialanalyzer.Util.MapUtils;
import com.socialanalyzer.accounts.controller.SocialAccountController;
import com.socialanalyzer.accounts.dto.SocialAccountDTO;
import com.socialanalyzer.analytics.dto.SocialAnalyticsDTO;
import com.socialanalyzer.analytics.entity.SocialAnalytics;
import com.socialanalyzer.analytics.repository.SocialAnalyticsRepository;
import com.socialanalyzer.analytics.service.SocialAnalyticsService;
import com.socialanalyzer.clients.InstagramClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class SocialAnalyticsServiceImpl implements SocialAnalyticsService {

    private final SocialAnalyticsRepository repository;
    private final SocialAccountController controller;
    private final InstagramClient client;

    @Autowired
    public SocialAnalyticsServiceImpl(SocialAnalyticsRepository repository,SocialAccountController controller,
                                      InstagramClient client)
    {
        this.repository = repository;
        this.controller = controller;
        this.client = client;
    }


    @Override
    public SocialAnalyticsDTO syncAnalytics(String postId) throws Exception {
        log.info("Inside @class SocialAnalyticsServiceImpl @method syncAnalytics param :{}", postId);
        if(StringUtils.isBlank(postId))
        {
            throw new IllegalArgumentException("Post Id can not be null or empty");
        }
        try {

            SocialAnalytics socialAnalytics = repository.findByPostId(postId).orElseThrow(() -> new RuntimeException("Can not find social analytics"));

            if (StringUtils.isBlank(socialAnalytics.getAccountId())) {
                throw new RuntimeException("AccountId not found for post: " + postId);
            }

            ResponseEntity<SocialAccountDTO> socialAccountDto = controller.findByAccountId(socialAnalytics.getAccountId());

            //Post Insights
            /*
            * get post insights form the instagram client
            * @Param postId and accessToken
            * then map their metric : impression, reach , saved...
            * */

            Map<String, Object> postsInsights = client.getPostsInsights(postId, "impressions,reach,saved,likes,comments,shares",socialAccountDto.getBody().getAccessToken());

            List<Map<String, Object>> data = (List<Map<String, Object>>) postsInsights.get("data");
            if(data!= null)
            {
                for(Map<String, Object> metric : data)
                {
                    String name = (String) metric.get("name");
                    List<Map<String, Object>> values = (List<Map<String, Object>>) metric.get("value");
                    Long value= values != null && !values.isEmpty() ?((Long)  values.get(0).get("value")) :0L;

                    switch (name)
                    {
                        case "impression" :
                            socialAnalytics.setImpressions(value);
                            break;

                        case "reach" :
                            socialAnalytics.setReach(value);
                            break;

                        case "saved":
                            socialAnalytics.setSaves(value);
                            break;

                        case "likes":
                            socialAnalytics.setLikes(value);
                            break;

                        case "comments":
                            socialAnalytics.setComments(value);
                            break;

                        case "shares":
                            socialAnalytics.setShares(value);
                            break;

                        default:
                            log.warn("Unknown media metric: {}", name);
                    }
                }
            }

           /*
           * Calculate engagementRate
           * first engagement = likes + comments + shares;
           * engagementRate = (engagement / total reach ) * 100;
           * */

            long engagements = socialAnalytics.getLikes() + socialAnalytics.getShares() + socialAnalytics.getComments();

           Double engagementsRate = 0.0;
           if(socialAnalytics.getReach() != null && socialAnalytics.getReach() > 0)
           {
              engagementsRate = (((double) engagements / socialAnalytics.getReach()) * 100);
           }

           socialAnalytics.setEngagementRate(engagementsRate);


            /*
                Update time to current time
             */
            socialAnalytics.setLastUpdatedAt(LocalDateTime.now());

            /*
            * Saved to the DB
            * */
            socialAnalytics = repository.saveAndFlush(socialAnalytics);

            return MapUtils.entityToDto(socialAnalytics, SocialAnalyticsDTO.class);
        }catch (Exception e) {
            log.error("Error while syncing analytics for postId {}: {}", postId, e.getMessage(), e);
            throw new RuntimeException("Error syncing analytics");
        }
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
