package com.dsmbamboo.api.domains.users.service;

import com.dsmbamboo.api.domains.users.dto.UserTokenResponse;
import com.dsmbamboo.api.domains.users.dto.UserSignInRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    UserTokenResponse signIn(UserSignInRequest data);

}
