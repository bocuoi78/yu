package com.vku.bocuoi.yu.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendWithHTMLBody(String to, String subject, String body);
    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
    void sendPasswordResetEmail(String toEmail, String resetLink);
}
