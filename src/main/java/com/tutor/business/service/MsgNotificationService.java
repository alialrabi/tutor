package com.tutor.business.service;

import com.tutor.persistance.entity.MsgNotification;
import com.tutor.persistance.repository.MsgNotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MsgNotificationService {

    private final MsgNotificationRepository msgNotificationRepository;
}
