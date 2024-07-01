package com.intern.project.freshermanagement.service.impl;

import com.intern.project.freshermanagement.common.exception.SendEmailFailureException;
import com.intern.project.freshermanagement.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Value("${url.redirect.active.user}")
    private String urlRedirect;

    private final JavaMailSender mailSender;

    @Override
    public void sendActiveUserMail(String email) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        String activeUrl = urlRedirect + "?email=" + email;
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email);
            helper.setSubject("Chào mừng đến với kì thực tập cùng VMO");
            helper.setSentDate(new Date());
            helper.setText(createActiveUserMailContent(activeUrl), true);
            ClassPathResource classPathResource = new ClassPathResource("static/vmo.png");
            helper.addInline("imageUrl", classPathResource);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new SendEmailFailureException(email);
        }
    }

    @Override
    public void sendResetPassword(String email, String password) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email);
            helper.setSubject("fresher management reset password");
            helper.setSentDate(new Date());
            helper.setText(createResetPasswordContent(password), true);
            ClassPathResource classPathResource = new ClassPathResource("static/vmo.png");
            helper.addInline("imageUrl", classPathResource);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new SendEmailFailureException(email);
        }
    }


    private String createActiveUserMailContent(String activeUrl) {
        return "";
    }

    private String createResetPasswordContent(String password) {
        return "";
    }

}
