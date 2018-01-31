package com.stellar.spring.poolriders.service;

import com.stellar.spring.poolriders.model.User;
import com.stellar.spring.poolriders.web.dto.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
