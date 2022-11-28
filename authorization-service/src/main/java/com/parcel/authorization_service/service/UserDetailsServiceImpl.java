package com.parcel.authorization_service.service;

import com.parcel.authorization_service.dto.security.UserSecurityDetailsDto;
import com.parcel.authorization_service.entity.UserEntity;
import com.parcel.authorization_service.repository.UserRepository;
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
