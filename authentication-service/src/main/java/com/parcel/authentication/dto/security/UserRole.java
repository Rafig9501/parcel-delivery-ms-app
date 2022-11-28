package com.parcel.authentication.dto.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {

    USER(new HashSet<>(List.of(UserPermission.USER))),
    ADMIN(new HashSet<>(List.of(UserPermission.ADMIN))),

    COURIER(new HashSet<>(List.of(UserPermission.COURIER)));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return this.permissions.stream().map(authority ->
                new SimpleGrantedAuthority(authority.name())).collect(Collectors.toSet());
    }
}
