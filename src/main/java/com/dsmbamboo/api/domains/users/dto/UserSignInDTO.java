package com.dsmbamboo.api.domains.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public UsernamePasswordAuthenticationToken getAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

}
