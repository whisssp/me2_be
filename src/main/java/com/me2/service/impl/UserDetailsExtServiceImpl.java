package com.me2.service.impl;

import com.me2.entity.CustomUserDetails;
import com.me2.entity.User;
import com.me2.repository.UserRepository;
import com.me2.service.UserDetailsExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsExtServiceImpl implements UserDetailsExtService {

    @Autowired
    private final UserRepository userRepository;

    public UserDetailsExtServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findFirstByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserDetails(user);
    }

    @Override
    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findById(Long.parseLong(id)).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(id);
        }
        return new CustomUserDetails(user);
    }
}