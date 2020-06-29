package com.dsmbamboo.api.domains.users.service;

import com.dsmbamboo.api.domains.users.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findByUsername(String username);
    Optional<User> findByDeviceTokenAndRolesContaining(String deviceToken, String role);
    Optional<User> findByUsernameAndRawPassword(String username, String password);
    Optional<User> findByUsernameAndRefreshToken(String username, String refreshToken);

    User save(User user);

    void updateDeviceToken(String deviceToken);

}
