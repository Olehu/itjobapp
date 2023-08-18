package com.itjobapp.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobAppUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(userName);
        GrantedAuthority authority = getUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authority);
    }

    private GrantedAuthority getUserAuthority(RoleEntity userRole) {
        return new SimpleGrantedAuthority(userRole.getRole());
    }

    private UserDetails buildUserForAuthentication(UserEntity user, GrantedAuthority authority) {
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(authority)
        );
    }
}