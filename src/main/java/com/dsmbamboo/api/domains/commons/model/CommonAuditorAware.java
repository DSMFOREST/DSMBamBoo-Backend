package com.dsmbamboo.api.domains.commons.model;

import com.dsmbamboo.api.domains.users.security.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class CommonAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (isUnauthenticated(auth))
            return Optional.empty();

        if (auth.getPrincipal() instanceof UserPrincipal) {
            return Optional.of(((UserPrincipal) auth.getPrincipal()).getId().toString());
        } else {
            return Optional.of(auth.getName());
        }
    }

    private boolean isUnauthenticated(Authentication authentication) {
        return authentication == null || !authentication.isAuthenticated();
    }

}
