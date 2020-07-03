package com.dsmbamboo.api.domains.users.service;

import com.dsmbamboo.api.domains.users.model.User;
import com.dsmbamboo.api.domains.users.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
        return findByUsername(username)
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

}
