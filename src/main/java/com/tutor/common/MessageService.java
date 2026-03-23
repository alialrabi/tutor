package com.tutor.common;


public interface MessageService {

    Boolean sendNotification( String to, String subject, String body);
    void resendNotifications();
}
