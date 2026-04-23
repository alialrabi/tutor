package com.tutor.schedule;

import com.tutor.common.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MsgNotificationSchedule {
    private final MessageService messageService;

    @Scheduled(fixedDelay = 300000) // every 5 minutes after last run
    public void retryUnsent() {
        messageService.resendNotifications();
    }
}
