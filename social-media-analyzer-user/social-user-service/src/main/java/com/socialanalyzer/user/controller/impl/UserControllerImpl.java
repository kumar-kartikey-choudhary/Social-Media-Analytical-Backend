package com.socialanalyzer.user.controller.impl;

import com.socialanalyzer.user.controller.UserController;
import com.socialanalyzer.user.dto.LoginRequest;
import com.socialanalyzer.user.dto.LoginResponse;
import com.socialanalyzer.user.dto.UserDTO;
import com.socialanalyzer.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService)
    {
        this.userService = userService;
    }


    @Override
    public ResponseEntity<UserDTO> register(UserDTO userDTO) {
        return ResponseEntity.ok(this.userService.register(userDTO));
    }

    @Override
    public ResponseEntity<LoginResponse> request(LoginRequest loginRequest) {
        return ResponseEntity.ok(this.userService.request(loginRequest));
    }

    @Override
    public ResponseEntity<UserDTO> getById(String id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }
}
