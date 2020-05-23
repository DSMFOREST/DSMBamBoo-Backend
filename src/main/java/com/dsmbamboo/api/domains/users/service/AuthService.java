package com.dsmbamboo.api.domains.users.service;

import com.dsmbamboo.api.domains.users.dto.AnonymousUserDeviceToken;
import com.dsmbamboo.api.domains.users.dto.UserTokenResponse;
import com.dsmbamboo.api.domains.users.dto.UserSignInRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    UserTokenResponse admin(UserSignInRequest data);
    UserTokenResponse anonymous(AnonymousUserDeviceToken data);

}
