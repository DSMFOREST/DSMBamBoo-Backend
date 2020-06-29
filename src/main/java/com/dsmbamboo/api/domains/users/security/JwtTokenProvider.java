package com.dsmbamboo.api.domains.users.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dsmbamboo.api.domains.users.exception.InvalidUserAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.exp.access}")
    private int accessTokenExpiration;

    @Value("${auth.jwt.exp.refresh}")
    private int refreshTokenExpiration;

    @Value("${auth.jwt.header}")
    private String header;

    @Value("${auth.jwt.prefix}")
    private String prefix;

    private final UserDetailsService userDetailsService;

    public String createAccessToken(String username, List<String> roles) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000))
                .withIssuedAt(new Date())
                .withIssuer("dsmbamboo")
                .withArrayClaim("roles", roles.toArray(String[]::new))
                .sign(Algorithm.HMAC512(secretKey));
    }

    public String createRefreshToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpiration * 1000))
                .withIssuedAt(new Date())
                .withIssuer("dsmbamboo")
                .withArrayClaim("roles", List.of("ROLE_REFRESH_TOKEN").toArray(String[]::new))
                .sign(Algorithm.HMAC512(secretKey));
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return JWT.require(Algorithm.HMAC512(secretKey))
                .build()
                .verify(token)
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(header);
        return (bearerToken != null && bearerToken.startsWith(prefix))
                ? bearerToken.substring(7) : null;
    }

    public boolean validateAccessToken(String accessToken) {
        try {
            return JWT.require(Algorithm.HMAC512(secretKey))
                    .withIssuer("dsmbamboo")
                    .build()
                    .verify(accessToken)
                    .getExpiresAt()
                    .after(new Date());
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new InvalidUserAuthenticationException();
        }
    }

}
