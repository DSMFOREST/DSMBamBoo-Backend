package com.dsmbamboo.api.domains.users.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserSignInDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
