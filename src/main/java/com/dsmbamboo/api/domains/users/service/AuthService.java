package com.dsmbamboo.api.domains.users.service;

import com.dsmbamboo.api.domains.users.dto.UserSignInRequest;
import com.dsmbamboo.api.domains.users.dto.UserTokenResponse;
import com.dsmbamboo.api.domains.users.dto.AdminSignInRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    UserTokenResponse adminSignIn(AdminSignInRequest data);
    UserTokenResponse userSignIn(UserSignInRequest data);

}
