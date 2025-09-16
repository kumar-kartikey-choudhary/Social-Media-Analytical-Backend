package com.socialanalyzer.accounts.service.impl;


import com.socialanalyzer.Util.MapUtils;
import com.socialanalyzer.enums.Platform;
import com.socialanalyzer.accounts.dto.SocialAccountDTO;
import com.socialanalyzer.accounts.model.SocialAccount;
import com.socialanalyzer.accounts.repository.SocialAccountRepository;
import com.socialanalyzer.accounts.service.SocialAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SocialAccountServiceImpl implements SocialAccountService {

    private final SocialAccountRepository repository;

    public SocialAccountServiceImpl(SocialAccountRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public SocialAccountDTO linkAccount(SocialAccountDTO socialAccountDTO) {

        log.info("Inside @class SocialAccountServiceImpl @method linkAccount @Param socialAccountDto :{}", socialAccountDTO);
        try {
            Objects.requireNonNull(socialAccountDTO, "Social Account Object can not be null or empty");
            if(StringUtils.isNotBlank(socialAccountDTO.getAccountId()))
            {
                throw new IllegalCallerException("Social Account Id must be null");
            }
            log.info("Map dto to entity");
            SocialAccount socialAccount = MapUtils.dtoToEntity(socialAccountDTO, SocialAccount.class);
            socialAccount = this.repository.saveAndFlush(socialAccount);


            return MapUtils.entityToDto(socialAccount, SocialAccountDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Can not map dto to entity");
        }
    }

    @Override
    public List<SocialAccountDTO> getUserAccounts(String userId) {
        log.info("Inside @class SocialAccountServiceImpl @method getUserAccount @Param userId :{}", userId);
        try {
            if(StringUtils.isBlank(userId))
            {
                throw new IllegalCallerException("Social Account Id can not be null");
            }
            List<SocialAccount> socialAccounts = repository.getByUserId(userId);

            return socialAccounts.stream().map(accounts -> {
                try {
                    return MapUtils.entityToDto(accounts, SocialAccountDTO.class);
                } catch (Exception e) {
                    throw new RuntimeException("Can not map entity to dto...");
                }
            }).toList();
        } catch (Exception e) {
            throw new RuntimeException("SOCIAL_ACCOUNT_NOT_FOUND");
        }
    }

    @Override
    public SocialAccountDTO getByUserIdAndPlatform(String userId, Platform platform) throws Exception {
        log.info("Inside @class SocialAccountServiceImpl @method getByUserIdAndPlatform @Param userId :{} , @Param platform :{} ", userId,platform);
        if(StringUtils.isBlank(userId))
        {
            throw new IllegalCallerException("Social Account Id can not be null");
        }
        SocialAccount socialAccount = repository.getByUserIdAndPlatform(userId, platform).orElseThrow(() -> new RuntimeException("Can not get social account with that userId and platform"));

        return MapUtils.entityToDto(socialAccount, SocialAccountDTO.class);
    }
}
