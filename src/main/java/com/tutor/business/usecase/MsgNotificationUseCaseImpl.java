package com.tutor.business.usecase;

import com.tutor.business.service.MsgNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MsgNotificationUseCaseImpl implements MsgNotificationUseCase {

    private final MsgNotificationService msgNotificationService;

}
