package com.me2.rest;

import com.me2.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/customer")
public class MailController {
    private final MailService mailService;
    MailController(MailService mailService) {
        this.mailService = mailService;

    }

    @GetMapping("/send-mail")
    public ResponseEntity<?> sendEmail(@RequestParam("to") String email,
                                       @RequestParam("ms") String message) throws MessagingException {
        String subject = "Email testing from gwen";
        mailService.sendMail(email, subject, message);
        return ResponseEntity.ok("Successfully");
    }
}
