package com.dsmbamboo.api.domains.users.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByDeviceTokenAndRolesContaining(String deviceToken, String role);
    Optional<User> findByUsernameAndRefreshToken(String username, String refreshToken);

    @Query(value = "SELECT u.device_token FROM user u WHERE u.id = :id", nativeQuery = true)
    String findDeviceTokenById(@Param("id") Long id);

    @Query(value = "SELECT u.device_token FROM user u", nativeQuery = true)
    List<String> findAllDevicetokens();

    @Query(value = "SELECT u.device_token FROM user u WHERE u.roles LIKE %:role%", nativeQuery = true)
    List<String> findAllDevicetokensByRole(@Param("role") String role);

}
