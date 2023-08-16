package com.itjobapp.Security;

import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserJpaRepository userRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final PasswordEncoder passwordEncoderService;
    private final CandidateService candidateService;
    private final CompanyService companyService;

    @Override
    public void registerUser(String email, String password, String role) {

        Optional<RoleEntity> userRole = Optional.ofNullable(roleJpaRepository.findByRole(role.toUpperCase()));

        if(userRole.isEmpty()){
            userRole = Optional.ofNullable(new RoleEntity().withRole(role.toUpperCase()));
            roleJpaRepository.save(userRole.get());
        }


        UserEntity user = new UserEntity().builder()
                .email(email)
                .password(passwordEncoderService.encode(password))
                .roles(Collections.singleton(userRole.get()))
                .build();

        if(role.toUpperCase().equals("CANDIDATE")) {
            candidateService.createCandidateByMail(email);
        }
        userRepository.save(user);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return passwordEncoderService.matches(password, user.get().getPassword());
        }
        return false;
    }
}

