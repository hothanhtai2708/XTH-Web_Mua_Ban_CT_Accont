package com.example.rentalshop_xtt.Service;

import com.example.rentalshop_xtt.Data.Entity.MailInfo;
import jakarta.mail.MessagingException;


public interface MailerService {
    void sendOtpEmail(String to, String otp);
    void send(MailInfo mail) throws MessagingException;
    void send(String to, String subject, String body) throws MessagingException;
}
