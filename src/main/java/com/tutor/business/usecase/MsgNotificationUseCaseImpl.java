package com.tutor.business.usecase;

import com.tutor.business.service.MsgNotificationService;
import com.tutor.persistance.entity.MsgNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MsgNotificationUseCaseImpl implements MsgNotificationUseCase {

    private final MsgNotificationService msgNotificationService;

    @Transactional
    public void create(Boolean isSent, String to, String subject, String body) {
        msgNotificationService.create(isSent, to, subject, body);
    }
}
