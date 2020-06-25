package com.dsmbamboo.api.domains.users.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class ExceptionConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) {
        ExceptionHandlerFilter customFilter = new ExceptionHandlerFilter();
        http.addFilterBefore(customFilter, JwtTokenFilter.class);
    }

}
