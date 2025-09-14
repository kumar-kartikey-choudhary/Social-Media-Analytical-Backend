package com.socialanalyzer.user.service.impl;

import com.socialanalyzer.Util.JwtUtil;
import com.socialanalyzer.Util.MapUtils;
import com.socialanalyzer.service.CustomUserDetailService;
import com.socialanalyzer.user.dto.LoginRequest;
import com.socialanalyzer.user.dto.LoginResponse;
import com.socialanalyzer.user.dto.UserDTO;
import com.socialanalyzer.user.model.User;
import com.socialanalyzer.user.repository.UserRepository;
import com.socialanalyzer.user.service.CustomUserDetailServiceImpl;
import com.socialanalyzer.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final CustomUserDetailService service;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder,
                           AuthenticationManager manager , CustomUserDetailService service,
                           JwtUtil jwtUtil)
    {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.manager = manager;
        this.service = service;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public UserDTO register(UserDTO userDTO) {
        log.info("Inside @class UserServiceImpl @ method register Param userDto :{}",userDTO);
        try {
            Objects.requireNonNull(userDTO, "User object must not be null");
            if(StringUtils.isNotBlank(userDTO.getUuid()))
            {
                throw new IllegalCallerException("user id must not be null");
            }
            User user = MapUtils.dtoToEntity(userDTO, User.class);
            user.setPassword(encoder.encode(userDTO.getPassword()));

           user = userRepository.saveAndFlush(user);

           return MapUtils.entityToDto(user, UserDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LoginResponse request(LoginRequest loginRequest) {
        log.info("Inside @class UserServiceImpl @method request param loginRequest : {}", loginRequest);
        try {
            Objects.requireNonNull(loginRequest, "Login request object can not be null");

            manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Username not found...");
        }
        catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials");
        }
        UserDetails userDetails = service.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new LoginResponse(token);
    }

    @Override
    public UserDTO getById(String id) {
        log.info("Inside @class UserServiceImpl @method getById param id:{}",id);
        try{
            if(StringUtils.isNotBlank(id))
            {
                throw new IllegalArgumentException("User id must not be blank");
            }

           User user =  userRepository.getById(id);
            UserDTO userDTO = MapUtils.entityToDto(user, UserDTO.class);
            return userDTO;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
}
