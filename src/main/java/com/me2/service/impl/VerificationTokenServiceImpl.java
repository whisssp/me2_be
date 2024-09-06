package com.me2.service.impl;

import com.me2.entity.VerificationToken;
import com.me2.global.enums.VerificationTokenType;
import com.me2.repository.VerificationTokenRepository;
import com.me2.service.MailService;
import com.me2.service.UserService;
import com.me2.service.VerificationTokenService;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    private final UserService userService;

    private final MailService mailService;

    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository,
                                        UserService userService, MailService mailService) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.userService = userService;
        this.mailService = mailService;
    }

    @Override
    public VerificationToken save(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken sendCode(Long userId, VerificationTokenType type) throws MessagingException {
        VerificationToken verificationToken = initVerificationToken(userId, type);
        String email = userService.getOneUserById(userId).getEmail();
        sendVerificationToken("Nghia dep trai", email);
        return null;
    }

    @Override
    public VerificationToken resendCode(Long userId, VerificationTokenType type) {
        return null;
    }

    private VerificationToken initVerificationToken(Long userId, VerificationTokenType type) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUserId(userId);
        verificationToken.setType(type);
        verificationToken.setCode("asd");
        verificationToken.setExpiredDate(Instant.now().plus(2, ChronoUnit.DAYS));
        return verificationToken;
    }

    @Async
    protected void sendVerificationToken(String token, String receiverEmail) throws MessagingException {
        mailService.sendMail(receiverEmail, "test code verification", token);
    }
}