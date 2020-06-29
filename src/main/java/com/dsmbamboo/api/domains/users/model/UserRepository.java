package com.dsmbamboo.api.domains.users.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByDeviceTokenAndRolesContaining(String deviceToken, String role);
    Optional<User> findByUsernameAndRefreshToken(String username, String refreshToken);

}
