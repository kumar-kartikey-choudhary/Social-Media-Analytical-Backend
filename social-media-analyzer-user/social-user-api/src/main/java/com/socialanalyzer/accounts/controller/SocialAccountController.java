package com.socialanalyzer.accounts.controller;


import com.socialanalyzer.enums.Platform;
import com.socialanalyzer.accounts.dto.SocialAccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseBody
@FeignClient(name = "social-account-service", path = "/socialAccounts")
public interface SocialAccountController {

    @PostMapping(path = "/link")
    ResponseEntity<SocialAccountDTO> linkAccount(@RequestBody SocialAccountDTO socialAccountDTO);

    @GetMapping(path = "getByUserId/{userId}")
    ResponseEntity<List<SocialAccountDTO>> getUserAccounts(@PathVariable(name = "userId") String userId);


    @GetMapping(path = "getByUserIdAndPlatform/{userId}/{platform}")
    ResponseEntity<SocialAccountDTO> getByUserIdAndPlatform(@PathVariable(name = "userId") String userId, @PathVariable(name = "platform")Platform platform);
}
