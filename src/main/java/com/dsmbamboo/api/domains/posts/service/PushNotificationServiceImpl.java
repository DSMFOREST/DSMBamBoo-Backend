package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.users.service.UserService;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PushNotificationServiceImpl implements PushNotificationService {

    private final UserService userService;

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
            if (!e.getErrorCode().equals("404"))
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
            if (!e.getErrorCode().equals("404"))
                e.printStackTrace();
        }
    }

    @Override
    public void sendToAdministrators(String title, String content) {
        List<String> deviceTokens = userService.findAllDeviceTokensByRole("ROLE_ADMIN");
        this.sendToMultipleDevices(deviceTokens, title, content);
    }

    @Override
    public void sendToAnonymousUsers(String title, String content) {
        List<String> deviceTokens = userService.findAllDeviceTokensByRole("ROLE_ANONYMOUS");
        this.sendToMultipleDevices(deviceTokens, title, content);
    }

    @Override
    public void sendToSingleDeviceByUserId(Long userId, String title, String content) {
        userService.findById(userId)
                .ifPresent(user -> this.sendToSingleDevice(user.getDeviceToken(), title, content));
    }

}
