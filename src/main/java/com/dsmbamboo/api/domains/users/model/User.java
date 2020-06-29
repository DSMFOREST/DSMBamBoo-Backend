package com.dsmbamboo.api.domains.users.model;

import com.dsmbamboo.api.domains.commons.model.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String roles;

    @Column
    private String permissions;

    @Column(name = "active_flag")
    private boolean isActive;

    @Size(max = 1000)
    @Column
    private String deviceToken;

    @Size(max = 1000)
    @Column
    private String refreshToken;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public void refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static User anonymous(String deviceToken, String encodedPassword) {
        return User.builder()
                .username(deviceToken)
                .password(encodedPassword)
                .deviceToken(deviceToken)
                .roles("ROLE_ANONYMOUS")
                .permissions("READ_UPLOADED_ARTICLE,CREATE_UPLOAD_REQUEST")
                .isActive(true)
                .build();
    }

    public List<String> getRoles() {
        return Arrays.asList(this.roles.split(","));
    }

    public List<String> getPermissions() {
        return Arrays.asList(this.permissions.split(","));
    }

}
