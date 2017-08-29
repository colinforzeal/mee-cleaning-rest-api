package com.mee.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "demo.security.jwt")
@Getter
@Setter
public class JwtSettings {
    private Integer tokenExpirationTime;
    private String tokenSigningKey;
    private Integer refreshTokenExpTime;
}
