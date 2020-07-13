package com.dsmbamboo.api.domains.posts.service;

import java.util.List;

public interface PushNotificationService {

    void sendToMultipleDevices(List<String> deviceTokens, String title, String content);

}
