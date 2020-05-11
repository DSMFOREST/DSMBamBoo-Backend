package com.dsmbamboo.api.domains.users.service;

import com.dsmbamboo.api.domains.users.dto.UserAuthTokenDTO;
import com.dsmbamboo.api.domains.users.dto.UserSignInDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    UserAuthTokenDTO signIn(UserSignInDTO data);

}
