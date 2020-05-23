package com.dsmbamboo.api.domains.users.controller;

import com.dsmbamboo.api.domains.users.dto.AnonymousUserDeviceToken;
import com.dsmbamboo.api.domains.users.dto.UserTokenResponse;
import com.dsmbamboo.api.domains.users.dto.UserSignInRequest;
import com.dsmbamboo.api.domains.users.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin")
    public UserTokenResponse admin(@RequestBody @Valid UserSignInRequest data) {
        return authService.admin(data);
    }

    @PostMapping("/anonymous")
    public UserTokenResponse anonymous(@RequestBody @Valid AnonymousUserDeviceToken data) {
        return authService.anonymous(data);
    }

}
