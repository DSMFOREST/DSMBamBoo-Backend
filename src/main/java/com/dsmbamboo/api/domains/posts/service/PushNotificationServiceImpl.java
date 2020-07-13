package com.dsmbamboo.api.domains.posts.service;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

    @Override
    public void sendToSingleDevice(String deviceToken, String title, String context) {
        Notification notification = new Notification(title, context);
        Message message = Message.builder()
                .setNotification(notification)
                .setToken(deviceToken)
                .build();
        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }

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
