package com.mee.security.model.token;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.mee.security.model.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.mee.security.config.JwtSettings;
import com.mee.security.model.CompanyContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenFactory {
    private final JwtSettings settings;

    @Autowired
    public JwtTokenFactory(JwtSettings settings) {
        this.settings = settings;
    }

    public AccessJwtToken createAccessJwtTokenForCompany(CompanyContext companyContext) {
        if (StringUtils.isBlank(companyContext.getEmail()))
            throw new IllegalArgumentException("Cannot create JWT Token without email");

        return createTokenFromSubject(companyContext.getEmail(), companyContext.getAuthorities());
    }

    public AccessJwtToken createAccessJwtTokenForUser(UserContext userContext) {
        if (StringUtils.isBlank(userContext.getFacebookId()))
            throw new IllegalArgumentException("Cannot create JWT Token without facebookId");

        return createTokenFromSubject(userContext.getFacebookId(), userContext.getAuthorities());
    }

    private AccessJwtToken createTokenFromSubject(String subject, List<GrantedAuthority> grantedAuthorities) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", grantedAuthorities.stream().map(s -> s.toString()).collect(Collectors.toList()));

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(settings.getTokenExpirationTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();

        return new AccessJwtToken(token, claims);
    }
}
