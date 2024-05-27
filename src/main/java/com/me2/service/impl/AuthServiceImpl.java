package com.me2.service.impl;

import com.me2.jwt.JwtProvider;
import com.me2.rest.vm.LoginVM;
import com.me2.rest.vm.UserEntityVM;
import com.me2.global.enums.EnumUserRole;
import com.me2.service.AuthService;
import com.me2.service.UserDetailsExtService;
import com.me2.service.UserService;
import com.me2.service.dto.LoginDTO;
import com.me2.service.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
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

    private final UserDetailsExtService userDetailsServiceExt;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    public AuthServiceImpl(UserDetailsExtService userDetailsServiceExt,
                           UserService userService,
                           AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userDetailsServiceExt = userDetailsServiceExt;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
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
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String token = jwtUtil.generateToken(userDetails);
        String token = jwtProvider.generateToken(SecurityContextHolder.getContext().getAuthentication(), loginDTO.getRememberMe(), null);
        return new LoginVM(token);
    }

    @Override
    public UserEntityVM register(UserDTO userDTO, EnumUserRole role) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userService.save(userDTO, role);
    }


}