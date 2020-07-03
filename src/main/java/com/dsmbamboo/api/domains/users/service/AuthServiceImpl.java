package com.dsmbamboo.api.domains.users.service;

import com.dsmbamboo.api.domains.users.dto.AdminSignInRequest;
import com.dsmbamboo.api.domains.users.dto.UserSignInRequest;
import com.dsmbamboo.api.domains.users.dto.UserTokenResponse;
import com.dsmbamboo.api.domains.users.exception.InvalidRefreshTokenException;
import com.dsmbamboo.api.domains.users.model.User;
import com.dsmbamboo.api.domains.users.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserTokenResponse adminSignIn(AdminSignInRequest data) {
        try {
            authenticationManager.authenticate(data.getAuthenticationToken());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getLocalizedMessage());
        }

        return userService.findByUsernameAndRawPassword(data.getUsername(), data.getPassword())
                .map(user -> {
                    user.refreshToken(jwtTokenProvider.createRefreshToken(user.getUsername()));
                    return userService.save(user);
                }).map(user -> {
                    String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), user.getRoles());
                    return new UserTokenResponse(accessToken, user.getRefreshToken());
                }).orElseThrow(() -> new UsernameNotFoundException("Username not found."));

    }

    @Override
    public UserTokenResponse userSignIn(UserSignInRequest data) {
        return userService.findByDeviceTokenAndRolesContaining(data.getDeviceToken(), "ROLE_ANONYMOUS")
                .or(() -> Optional.of(User.anonymous(data.getDeviceToken(), passwordEncoder.encode(data.getDeviceToken()))))
                .map(user -> {
                    if (isAlreadySignedUser(user))
                        authenticate(data.getAuthenticationToken());

                    user.refreshToken(jwtTokenProvider.createRefreshToken(user.getUsername()));
                    return userService.save(user);
                }).map(user -> {
                    String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), user.getRoles());
                    return new UserTokenResponse(accessToken, user.getRefreshToken());
                }).get();
    }

    private boolean isAlreadySignedUser(User user) {
        return user.getId() != null;
    }

    private void authenticate(UsernamePasswordAuthenticationToken authToken) {
        try {
            authenticationManager.authenticate(authToken);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getLocalizedMessage());
        }
    }

    @Override
    public UserTokenResponse refreshToken(String refreshToken) {
        String username = jwtTokenProvider.validateRefreshTokenAndGetIdentity(refreshToken);
        return userService.findByUsernameAndRefreshToken(username, refreshToken)
                .map(user -> {
                    user.refreshToken(jwtTokenProvider.createRefreshToken(username));
                    return userService.save(user);
                }).map(user -> {
                    String accessToken = jwtTokenProvider.createAccessToken(username, user.getRoles());
                    return new UserTokenResponse(accessToken, user.getRefreshToken());
                }).orElseThrow(InvalidRefreshTokenException::new);
    }

}
