package com.tutor.business.service;

import com.tutor.persistance.entity.MsgNotification;
import com.tutor.persistance.repository.MsgNotificationRepository;
import com.tutor.security.AppUserDetails;
import com.tutor.security.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MsgNotificationService {

    private final MsgNotificationRepository msgNotificationRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create(Boolean isSent, String to, String subject, String body) {
        MsgNotification notification = new MsgNotification();
        notification.setBody(body);
        notification.setIsSent(isSent);
        notification.setReceiverEmail(to);
        notification.setSenderEmail(SecurityUtil.getCurrentData(AppUserDetails::getEmail));
        notification.setSubject(subject);
        msgNotificationRepository.save(notification);
    }
}
