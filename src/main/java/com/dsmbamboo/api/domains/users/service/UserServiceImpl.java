package com.dsmbamboo.api.domains.users.service;

import com.dsmbamboo.api.domains.users.model.User;
import com.dsmbamboo.api.domains.users.model.UserRepository;
import com.dsmbamboo.api.domains.users.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByDeviceTokenAndRolesContaining(String deviceToken, String role) {
        return userRepository.findByDeviceTokenAndRolesContaining(deviceToken, role);
    }

    @Override
    public Optional<User> findByUsernameAndRawPassword(String username, String rawPassword) {
        return this.findByUsername(username)
                .map(user -> (passwordEncoder.matches(rawPassword, user.getPassword())) ? user : null);
    }

    @Override
    public Optional<User> findByUsernameAndRefreshToken(String username, String refreshToken) {
        return userRepository.findByUsernameAndRefreshToken(username, refreshToken);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateDeviceToken(String deviceToken) {
        this.findByUsername(authenticationFacade.getUsername())
                .ifPresent(user -> {
                    user.updateDeviceToken(deviceToken);
                    userRepository.save(user);
                });
    }

}
