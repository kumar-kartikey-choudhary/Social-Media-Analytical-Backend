package com.socialanalyzer.user.service;

import com.socialanalyzer.service.CustomUserDetailService;
import com.socialanalyzer.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailServiceImpl implements CustomUserDetailService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("USER_NOT_FOUND"));
    }
}
