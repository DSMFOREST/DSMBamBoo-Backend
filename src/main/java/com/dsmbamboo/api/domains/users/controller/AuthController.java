package com.dsmbamboo.api.domains.users.controller;

import com.dsmbamboo.api.domains.users.dto.AdminSignInRequest;
import com.dsmbamboo.api.domains.users.dto.UserSignInRequest;
import com.dsmbamboo.api.domains.users.dto.UserTokenResponse;
import com.dsmbamboo.api.domains.users.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/admin/auth/signin")
    public UserTokenResponse admin(@RequestBody @Valid AdminSignInRequest data) {
        return authService.adminSignIn(data);
    }

    @PostMapping("/auth/signin")
    public UserTokenResponse anonymous(@RequestBody @Valid UserSignInRequest data) {
        return authService.userSignIn(data);
    }

    @PatchMapping("/auth/refresh")
    public UserTokenResponse refreshToken(
            @RequestHeader("X-Refresh-Token") @NotEmpty String refreshToken) {
        return authService.refreshToken(refreshToken);
    }

}
