package com.parcel.authentication.mapper.qualifier;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperQualifier {

    private final PasswordEncoder passwordEncoder;

    @Named(value = "hashPassword")
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
