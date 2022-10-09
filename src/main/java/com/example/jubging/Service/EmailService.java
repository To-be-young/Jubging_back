package com.example.jubging.Service;

import javax.mail.internet.MimeMessage;

public interface EmailService {
    String createKey();
    void sendSimpleMessage(String email) throws Exception;
    boolean verifyEmailCode(String email, String code);
    void refreshVerifyEmailCode(String email) throws Exception;
}
