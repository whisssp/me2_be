package com.me2.rest.common;

import com.me2.global.enums.VerificationTokenType;
import com.me2.rest.common.vm.VerificationTokenCommonVM;
import com.me2.service.VerificationTokenService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
@Slf4j
public class VerificationTokenCommonController {

    private final VerificationTokenService verificationTokenService;

    public VerificationTokenCommonController(VerificationTokenService verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }

    /**
     * GET: Request to *send* verify code by type
     * @param userId
     * @param type
     * @return
     */
    @GetMapping("/verification/send-code")
    public ResponseEntity<Void> sendVerifyCode(@RequestParam("userId") Long userId,
                                                @RequestParam("type") VerificationTokenType type) throws MessagingException {
        log.debug("REST to *SEND* verify code to user: {} - code type: {}", userId, type);
        verificationTokenService.sendCode(userId, type);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET: Request to *resend* verify code by type
     * @param userId
     * @param type
     * @return
     */
    @GetMapping("/verification/resend-code")
    public ResponseEntity<Void> resendVerifyCode(@RequestParam("userId") Long userId,
                                                  @RequestParam("type")VerificationTokenType type) {
        log.debug("REST to *RESEND* verify code to user: {} - code type: {}", userId, type);
        verificationTokenService.resendCode(userId, type);
        return ResponseEntity.noContent().build();
    }

}