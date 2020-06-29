package com.dsmbamboo.api.domains.users.controller;

import com.dsmbamboo.api.domains.users.dto.UpdateDeviceTokenRequest;
import com.dsmbamboo.api.domains.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/me/device-token")
    public void updateMyDeviceToken(@RequestBody @Valid UpdateDeviceTokenRequest request) {
        userService.updateDeviceToken(request.getDeviceToken());
    }

}
