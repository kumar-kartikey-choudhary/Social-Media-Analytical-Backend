package com.socialanalyzer.social.controller.impl;

import com.socialanalyzer.enums.Platform;
import com.socialanalyzer.social.controller.SocialAccountController;
import com.socialanalyzer.social.dto.SocialAccountDTO;
import com.socialanalyzer.social.service.SocialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/socialAccounts")
public class SocialAccountControllerImpl implements SocialAccountController {

    private final SocialAccountService service;

    @Autowired
    public SocialAccountControllerImpl(SocialAccountService service)
    {
        this.service = service;
    }


    @Override
    public ResponseEntity<SocialAccountDTO> linkAccount(SocialAccountDTO socialAccountDTO) {
        return ResponseEntity.ok(this.service.linkAccount(socialAccountDTO));
    }

    @Override
    public ResponseEntity<List<SocialAccountDTO>> getUserAccounts(String userId) {
        return ResponseEntity.ok(this.service.getUserAccounts(userId));
    }

    @Override
    public ResponseEntity<SocialAccountDTO> getByUserIdAndPlatform(String userId, Platform platform) {
        return ResponseEntity.ok(this.service.getByUserIdAndPlatform(userId,platform));
    }
}
