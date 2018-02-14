package com.stellar.spring.content.mgmt.service;

import com.stellar.spring.content.mgmt.model.User;
import com.stellar.spring.content.mgmt.web.dto.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
