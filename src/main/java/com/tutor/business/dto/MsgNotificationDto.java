package com.tutor.business.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgNotificationDto {
    private Long id;
    private String senderEmail;
    private String receiverEmail;
    private String subject;
    private String body;
}
