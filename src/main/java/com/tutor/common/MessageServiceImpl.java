package com.tutor.common;

import com.tutor.business.service.MsgNotificationService;
import com.tutor.persistance.entity.MsgNotification;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    @Value("${mail.smtp.username}")
    private String username;

    @Value("${mail.smtp.password}")
    private String password;

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private String port;

    @Value("${mail.smtp.auth}")
    private String auth;

    @Value("${mail.smtp.starttls.enable}")
    private String starttls;

    private final MsgNotificationService msgNotificationService;

    public Boolean sendNotification(String to, String subject, String body) {
        log.info("send Notification message to: {}", to);

        Properties props = new Properties();

        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        boolean isSent = false;

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            isSent = true;
            log.info("Message sent successfully to: {}", to);
        } catch (MessagingException e) {
            log.error("Filed to send email to: {}", to, e);
        } finally {
            msgNotificationService.create(isSent, to, subject, body);
        }
        return isSent;
    }

    public void resendNotifications() {
        log.info("resend All Notification messages");

        Properties props = new Properties();

        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        List<MsgNotification> nonSentNotifications = msgNotificationService.getAllMessages(false);

        for (MsgNotification notification: nonSentNotifications) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(notification.getReceiverEmail())
                );
                message.setSubject(notification.getSubject());
                message.setText(notification.getBody());

                Transport.send(message);

                notification.setIsSent(true);
            } catch (MessagingException e) {
                log.error("Failed to resend notification id={}: {}", notification.getId(), e.getMessage());
            }
        }
    }

}