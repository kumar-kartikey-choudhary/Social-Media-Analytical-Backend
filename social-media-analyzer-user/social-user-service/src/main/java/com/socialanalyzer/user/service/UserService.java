package com.socialanalyzer.user.service;

import com.socialanalyzer.user.dto.LoginRequest;
import com.socialanalyzer.user.dto.LoginResponse;
import com.socialanalyzer.user.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO userDTO);

    LoginResponse request(LoginRequest loginRequest);

    UserDTO getById(String id);
}
