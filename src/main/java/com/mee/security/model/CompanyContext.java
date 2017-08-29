package com.mee.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public class CompanyContext {
    private String email;
    private List<GrantedAuthority> authorities;

    public CompanyContext(String email, List<GrantedAuthority> authorities) {
        this.email = email;
        this.authorities = authorities;
    }
}
