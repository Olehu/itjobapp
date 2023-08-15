package com.itjobapp.Security;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserJpaRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;

    @Override
    public void registerUser(String email, String password) {
        UserEntity user = new UserEntity().builder()
                .email(email)
                .password(passwordEncoderService.encodePassword(password))
                .build();
        userRepository.save(user);
    }
}

