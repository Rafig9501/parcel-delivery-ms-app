package com.parcel.authorization_service.dto.security;

import com.parcel.authorization_service.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Log4j2
@ToString
@RequiredArgsConstructor
public class UserSecurityDetailsDto implements UserDetails {

    private final UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userEntity.getRole().equals("ADMIN")) {
            return UserRole.ADMIN.getAuthorities();
        } else if (userEntity.getRole().equals("COURIER")) {
            return UserRole.COURIER.getAuthorities();
        }
        return UserRole.USER.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userEntity.getIsNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userEntity.getIsNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEntity.getIsEnabled();
    }
}
