package com.dsmbamboo.api.domains.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;

    private String username;

    private String password;

    private boolean isActive;

    private String roles;

    private String permissions;

    private String deviceToken;

    private LocalDateTime createdAt;

    public List<String> getRoles() {
        if (!roles.isEmpty())
            return Arrays.asList(roles.split(","));
        return new ArrayList<>();
    }

    public List<String> getPermissions() {
        if (!roles.isEmpty())
            return Arrays.asList(roles.split(","));
        return new ArrayList<>();
    }

}
