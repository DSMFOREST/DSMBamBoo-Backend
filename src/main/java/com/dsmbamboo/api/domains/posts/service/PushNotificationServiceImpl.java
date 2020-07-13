package com.dsmbamboo.api.domains.posts.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

    @Override
    public void sendToMultipleDevices(List<String> deviceTokens, String title, String content) {
        Notification notification = new Notification(title, content);
        MulticastMessage message = MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(deviceTokens)
                .build();

        try {
            FirebaseMessaging.getInstance().sendMulticast(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }

}
