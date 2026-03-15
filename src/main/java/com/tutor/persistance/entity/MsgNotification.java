package com.tutor.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "msg_notification", schema = "tutor")
public class MsgNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_notification_seq_gen")
    @SequenceGenerator(
            name = "msg_notification_seq_gen",
            sequenceName = "tutor.msg_notification_seq",
            schema = "tutor",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "sender_email", nullable = false, length = 255)
    private String senderEmail;

    @Column(name = "receiver_email", nullable = false, length = 255)
    private String receiverEmail;

    @Column(name = "subject", length = 500)
    private String subject;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

}