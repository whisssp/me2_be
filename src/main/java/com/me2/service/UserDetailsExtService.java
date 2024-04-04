package com.me2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsExtService extends UserDetailsService {

    UserDetails loadUserById(String id);
}