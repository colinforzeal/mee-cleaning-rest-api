package com.mee.entity;

public enum Role {
    ROLE_ADMIN, ROLE_COMPANY, ROLE_USER;

    public String getAuthority() {
        return this.name();
    }
}
