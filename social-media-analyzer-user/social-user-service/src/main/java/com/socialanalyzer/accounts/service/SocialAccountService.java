package com.socialanalyzer.accounts.service;

import com.socialanalyzer.enums.Platform;
import com.socialanalyzer.accounts.dto.SocialAccountDTO;

import java.util.List;

public interface SocialAccountService {

    SocialAccountDTO linkAccount(SocialAccountDTO socialAccountDTO);

    List<SocialAccountDTO> getUserAccounts(String userId);

    SocialAccountDTO getByUserIdAndPlatform(String userId, Platform platform) throws Exception;

    SocialAccountDTO findByAccountId(String accountId);
}
