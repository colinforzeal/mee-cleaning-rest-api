package com.mee.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public class UserContext {
    private String facebookId;
    private List<GrantedAuthority> authorities;

    public UserContext(String facebookId, List<GrantedAuthority> authorities) {
        this.facebookId = facebookId;
        this.authorities = authorities;
    }

}
