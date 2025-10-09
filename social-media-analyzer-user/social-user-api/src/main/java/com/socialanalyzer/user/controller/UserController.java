package com.socialanalyzer.user.controller;


import com.socialanalyzer.user.dto.LoginRequest;
import com.socialanalyzer.user.dto.LoginResponse;
import com.socialanalyzer.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@FeignClient(name = "User/Auth-Service" , primary = false, url = "${user.url}")
public interface UserController {

    @PostMapping(path = "/register")
    ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO);

    @PostMapping(path = "/login")
    ResponseEntity<LoginResponse> request(@RequestBody LoginRequest loginRequest);

    @GetMapping(path = "find/{id}")
    ResponseEntity<UserDTO> getById(@PathVariable(name = "id") String id);
}
