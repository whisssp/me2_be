package com.me2.service;

import com.me2.entity.VerificationToken;
import com.me2.global.enums.VerificationTokenType;
import jakarta.mail.MessagingException;

public interface VerificationTokenService {

    VerificationToken save(VerificationToken verificationToken);

    VerificationToken sendCode(Long userId, VerificationTokenType type) throws MessagingException;

    VerificationToken resendCode(Long userId, VerificationTokenType type);
}