package com.dsmbamboo.api.domains.users.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dsmbamboo.api.domains.users.model.User;
import com.dsmbamboo.api.domains.users.model.UserRepository;
import com.dsmbamboo.api.domains.users.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    @Value("auth.jwt.secret")
    private String tokenSecret;

    @Value("auth.jwt.prefix")
    public String tokenPrefix;

    @Value("auth.jwt.header")
    public String headerString;

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(headerString);

        if (header == null || !header.startsWith(tokenPrefix)) {
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUserNamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);

        super.doFilterInternal(request, response, chain);
    }

    private Authentication getUserNamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(headerString);
        if (token != null) {
            String username = JWT.require(Algorithm.HMAC512(tokenSecret.getBytes()))
                    .build()
                    .verify(token.replace(tokenPrefix, ""))
                    .getSubject();

            if (username != null) {
                User user = userRepository.findByUsername(username);
                UserPrincipal principal = new UserPrincipal(user);
                return new UsernamePasswordAuthenticationToken(username, null, principal.getAuthorities());
            }
            return null;
        }
        return null;
    }

}
