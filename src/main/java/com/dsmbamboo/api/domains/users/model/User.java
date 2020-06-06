package com.dsmbamboo.api.domains.users.model;

import com.dsmbamboo.api.domains.commons.model.Auditable;
import com.dsmbamboo.api.utils.StringListConverter;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {

    @Id
    private String id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> roles;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> permissions;

    @Column(name = "active_flag")
    private boolean isActive;

    @Column
    private String deviceToken;

    @Column
    private String refreshToken;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public void refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
