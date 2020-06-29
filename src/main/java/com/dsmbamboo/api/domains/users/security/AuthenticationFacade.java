package com.dsmbamboo.api.domains.users.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthentication();
    String getUsername();

}
