package com.example.rentalshop_xtt.Service.ServiceImpl;

import com.example.rentalshop_xtt.Data.Entity.MailInfo;
import com.example.rentalshop_xtt.Repository.RegisterRepo;
import com.example.rentalshop_xtt.Service.MailerService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailerServiceImpl implements MailerService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HttpSession session;

    @Override
    public void sendOtpEmail(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Mã OTP cho đăng ký tài khoản");
            helper.setText("Mã OTP của bạn là: " + otp);
            mailSender.send(message);
            session.setAttribute("otp", otp);
            session.setAttribute("email", toEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void send(MailInfo mail) throws MessagingException {
        // Tạo message
        MimeMessage message = mailSender.createMimeMessage();

        // Sử dụng Helper để thiết lập các thông tin cần thiết cho message
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);
        helper.setReplyTo(mail.getFrom());

        String[] cc = mail.getCc();
        if (cc != null && cc.length > 0) {
            helper.setCc(cc);
        }

        String[] bcc = mail.getBcc();
        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc);
        }

        String[] attachments = mail.getAttachments();
        if (attachments != null && attachments.length > 0) {
            for (String path : attachments) {
                File file = new File(path);
                helper.addAttachment(file.getName(), file);
            }
        }
        // Gửi message đến SMTP server
        mailSender.send(message);
    }

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        this.send(new MailInfo(to, subject, body));
    }


    @Autowired
    private RegisterRepo registerRepo;

    public boolean isEmailExists(String email) {
        return registerRepo.findByEmail(email) != null;
    }

}
