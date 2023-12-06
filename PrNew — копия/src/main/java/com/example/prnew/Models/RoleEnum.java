package com.example.prnew.Models;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    USER, ADMIN, WAREHOUSE;
    @Override
    public String getAuthority()
    {
        return name();
    }

}
