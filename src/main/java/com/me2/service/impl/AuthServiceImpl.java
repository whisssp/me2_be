package com.me2.service.impl;

import com.me2.entity.Cart;
import com.me2.entity.ResetPasswordToken;
import com.me2.entity.User;
import com.me2.exception.CustomException;
import com.me2.exception.ResetTokenExpiredException;
import com.me2.global.enums.EnumError;
import com.me2.jwt.JwtProvider;
import com.me2.repository.ResetPasswordTokenRepository;
import com.me2.rest.common.mapper.UserVMMapper;
import com.me2.rest.common.vm.LoginVM;
import com.me2.rest.common.vm.UserEntityVM;
import com.me2.global.enums.EnumUserRole;
import com.me2.service.*;
import com.me2.service.dto.LoginDTO;
import com.me2.service.dto.NewPasswordDTO;
import com.me2.service.dto.ResetPasswordDTO;
import com.me2.service.dto.UserDTO;
import jakarta.mail.MessagingException;
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
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    private final MailService mailService;

    private final ResetPasswordTokenRepository passwordTokenRepository;

    private final ThymeleafService thymeleafService;


    public AuthServiceImpl(UserDetailsExtService userDetailsServiceExt,
                           UserService userService,
                           AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder, JwtProvider jwtProvider,
                           UserVMMapper userVMMapper, CartService cartService, MailService mailService, ResetPasswordTokenRepository passwordTokenRepository, ThymeleafService thymeleafService) {
        this.userDetailsServiceExt = userDetailsServiceExt;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.userVMMapper = userVMMapper;
        this.cartService = cartService;
        this.mailService = mailService;
        this.passwordTokenRepository = passwordTokenRepository;
        this.thymeleafService = thymeleafService;
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

    @Override
    public String forgotPassword(String email) throws MessagingException {
        log.info("--------forgot_password-------");

        User user = userService.findUserByEmail(email);
        ResetPasswordToken checkValid = passwordTokenRepository.findByUserId(user.getId());

        if(checkValid != null) {
            passwordTokenRepository.deleteById(checkValid.getId());
        }

        // Generate secret token for password reset
        String token = generateRandomCode();

        //set resetPasswordDTO
        ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO();
        resetPasswordDTO.setEmail(user.getEmail());
        resetPasswordDTO.setCode(token);
        saveResetPassword(resetPasswordDTO, user);

        sendEmail(user.getEmail(), token);
        return "Email sent successfully.";
    }

    @Override
    @Transactional(noRollbackFor = ResetTokenExpiredException.class)
    public String resetPassword(NewPasswordDTO dto) {
        log.info("--------resetPassword-------");
        User user = userService.findUserByEmail(dto.getEmail());

        validateResetCode(dto.getCode());

        String passwordTemp = passwordEncoder.encode(dto.getNewPassword());
        user.setPassword(passwordTemp);
        userService.saveForResetPassword(user);

        removeToken(user.getId(), null);
        return "Successfully";
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

    //Begin forgot-reset password
    private String generateRandomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    private void saveResetPassword(ResetPasswordDTO dto, User user) {

        ResetPasswordToken resetPasswordToken = new ResetPasswordToken();
        resetPasswordToken.setUser(user);
        resetPasswordToken.setResetCode(dto.getCode());
        resetPasswordToken.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(3)).toInstant());

        passwordTokenRepository.save(resetPasswordToken);
    }

    private void validateResetCode(String code) {
        ResetPasswordToken resetPasswordToken = passwordTokenRepository.findByResetCode(code);
        if(resetPasswordToken == null) {
            throw new CustomException(EnumError.CODE_RESET_WRONG);
        }

        if(resetPasswordToken.getExpiration().isBefore(new Date().toInstant())) {
            passwordTokenRepository.deleteById(resetPasswordToken.getId());
            throw new CustomException(EnumError.CODE_RESET_EXPIRED);
        }
    }

    @Async
    protected void removeToken(Long userId, String code) {
        passwordTokenRepository.deleteByUserId(userId, code);
    }

    @Async
    protected void sendEmail(String email, String token ) throws MessagingException {
        String resetUrl = "localhost:8080/api/v0/customer/reset-password";
        String body = thymeleafService.getContent(email, resetUrl, token);
        mailService.sendMail(email, "Reset Your Password", body);
    }
    //end forgot-reset password
}