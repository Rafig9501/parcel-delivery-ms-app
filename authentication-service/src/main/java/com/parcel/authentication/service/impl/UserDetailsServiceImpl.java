package com.parcel.authentication.service.impl;

import com.parcel.authentication.dto.security.UserSecurityDetailsDto;
import com.parcel.authentication.entity.UserEntity;
import com.parcel.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userId));
        return new UserSecurityDetailsDto(user);
    }
}
