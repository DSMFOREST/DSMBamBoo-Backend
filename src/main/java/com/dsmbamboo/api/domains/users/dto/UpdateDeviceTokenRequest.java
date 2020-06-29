package com.dsmbamboo.api.domains.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeviceTokenRequest {

    @NotEmpty
    private String deviceToken;

}
