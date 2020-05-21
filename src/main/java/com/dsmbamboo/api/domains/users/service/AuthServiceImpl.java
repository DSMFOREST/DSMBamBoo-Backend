package com.dsmbamboo.api.domains.users.service;

import com.dsmbamboo.api.domains.users.dto.UserTokenResponse;
import com.dsmbamboo.api.domains.users.dto.UserSignInRequest;
import com.dsmbamboo.api.domains.users.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserTokenResponse signIn(UserSignInRequest data) {
        try {
            authenticationManager.authenticate(data.getAuthenticationToken());
            return userService.findByUsernameAndRawPassword(data.getUsername(), data.getPassword())
                    .map(user -> {
                        user.refreshToken(jwtTokenProvider.createRefreshToken(user.getUsername()));
                        return userService.save(user);
                    }).map(user -> {
                        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), user.getRoles());
                        return new UserTokenResponse(accessToken, user.getRefreshToken());
                    }).orElseThrow(() -> new UsernameNotFoundException("Username not found."));

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password detected.");
        }
    }

}
