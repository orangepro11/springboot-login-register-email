package com.orange.logindemo.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm Your Email");
            helper.setFrom("orangejuicepro@163.com");
            mailSender.send(mimeMessage);
            System.out.println("the email is send");
        } catch (MessagingException e) {
            LOGGER.error("fail to send email", e);
            throw new IllegalStateException("fail to send email");
        }
    }

    @Async
    public void testAsync() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }

    }
}
