package com.me2.service;

import jakarta.mail.MessagingException;

public interface MailService {

    void sendMail(String to, String subject, String body) throws MessagingException;
}