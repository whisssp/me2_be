package com.me2.service.impl;

import com.me2.entity.Cart;
import com.me2.entity.User;
import com.me2.exception.CustomException;
import com.me2.global.enums.EnumError;
import com.me2.jwt.JwtProvider;
import com.me2.rest.mapper.UserVMMapper;
import com.me2.rest.vm.LoginVM;
import com.me2.rest.vm.UserEntityVM;
import com.me2.global.enums.EnumUserRole;
import com.me2.service.AuthService;
import com.me2.service.CartService;
import com.me2.service.UserDetailsExtService;
import com.me2.service.UserService;
import com.me2.service.dto.LoginDTO;
import com.me2.service.dto.UserDTO;
import com.me2.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserDetailsExtService userDetailsServiceExt;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    private final UserVMMapper userVMMapper;

    private final CartService cartService;

    public AuthServiceImpl(UserDetailsExtService userDetailsServiceExt,
                           UserService userService,
                           AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder, JwtProvider jwtProvider,
                           UserVMMapper userVMMapper, CartService cartService) {
        this.userDetailsServiceExt = userDetailsServiceExt;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.userVMMapper = userVMMapper;
        this.cartService = cartService;
    }

    @Override
    public LoginVM login(LoginDTO loginDTO) {
        log.info("Request to Login with: {}", loginDTO.toString());
        authenticate(loginDTO.getUsername(), loginDTO.getPassword());
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String token = jwtUtil.generateToken(userDetails);
        String token = jwtProvider.generateToken(SecurityContextHolder.getContext().getAuthentication(), loginDTO.getRememberMe(), null);
        return new LoginVM(token);
    }

    @Override
    public UserEntityVM register(UserDTO userDTO, EnumUserRole role) {
        String pass = userDTO.getPassword();
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserEntityVM userVM = null;
        if(role.equals(EnumUserRole.USER)) {
            User u = userService.saveForUser(userDTO).orElseThrow(()-> new CustomException(EnumError.USER_REGISTER_FAILED));
            initCart(u, pass);
            userVM = userVMMapper.toDto(u);
        } else if(role.equals(EnumUserRole.ADMIN)) {
            userVM = userService.save(userDTO, role);
        }
        return userVM;
    }

    @Async
    protected void initCart(User user, String pass) {
        authenticate(user.getEmail(), pass);
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            throw new AuthenticationCredentialsNotFoundException("Authenticate failed");
        Cart cart = new Cart();
        cart.setUser(user);
        cartService.saveForUser(cart);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetailsServiceExt.loadUserByUsername(username), password);
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            log.error("Err/Login: {}", e.getMessage());
            throw new BadCredentialsException("Invalid Username or Password!");
        }
    }
}