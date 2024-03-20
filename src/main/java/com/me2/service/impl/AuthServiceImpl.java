package com.me2.service.impl;

import com.me2.controller.vm.LoginVM;
import com.me2.controller.vm.UserEntityVM;
import com.me2.entity.CustomUserDetails;
import com.me2.repository.UserRepository;
import com.me2.service.AuthService;
import com.me2.service.UserDetailsServiceExt;
import com.me2.service.UserService;
import com.me2.service.dto.LoginDTO;
import com.me2.service.dto.UserDTO;
import com.me2.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserDetailsServiceExt userDetailsServiceExt;

    @Autowired
    private final UserService userService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserDetailsServiceExt userDetailsServiceExt,
                           UserService userService,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder) {
        this.userDetailsServiceExt = userDetailsServiceExt;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginVM login(LoginDTO loginDTO) {
        log.info("Request to Login with: {}", loginDTO.toString());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetailsServiceExt.loadUserByUsername(loginDTO.getUsername()), loginDTO.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            log.error("Err/Login: {}", e.getMessage());
            throw new BadCredentialsException("Invalid Username or Password!");
        }
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        return new LoginVM(token);
    }

    @Override
    public UserEntityVM register(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userService.create(userDTO);
    }


}