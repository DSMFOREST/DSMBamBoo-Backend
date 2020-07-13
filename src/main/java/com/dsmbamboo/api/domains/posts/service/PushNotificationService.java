package com.dsmbamboo.api.domains.posts.service;

import java.util.List;

public interface PushNotificationService {

    void sendToSingleDevice(String deviceToken, String title, String context);
    void sendToMultipleDevices(List<String> deviceTokens, String title, String content);
    void sendToAdministrators(String title, String content);
    void sendToAnonymousUsers(String title, String content);
    void sendToSingleDeviceByUserId(Long userId, String title, String content);

}
