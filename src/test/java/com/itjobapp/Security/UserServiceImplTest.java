package com.itjobapp.Security;

import com.itjobapp.Service.CandidateService;
import com.itjobapp.Service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserJpaRepository userRepository;

    @Mock
    private RoleJpaRepository roleJpaRepository;

    @Mock
    private PasswordEncoder passwordEncoderService;

    @Mock
    private CandidateService candidateService;

    @Mock
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        String email = "test@example.com";
        String password = "password";
        String role = "CANDIDATE";
        String encodedPassword = "encodedPassword";

        when(roleJpaRepository.findByRole(role)).thenReturn(null);
        when(passwordEncoderService.encode(password)).thenReturn(encodedPassword);

        userService.registerUser(email, password, role);

        verify(roleJpaRepository).save(any(RoleEntity.class));
        verify(candidateService).createCandidateByMail(email);
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void testAuthenticateUser() {
        String email = "test@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(encodedPassword);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(passwordEncoderService.matches(password, encodedPassword)).thenReturn(true);

        boolean result = userService.authenticateUser(email, password);

        assertTrue(result);
    }

    @Test
    void testUpdateEmail() {
        String email = "test@example.com";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);

        userService.updateEmail(email);

        verify(userRepository).save(userEntity);
    }

    @Test
    void testFindRoleByMail() {
        String email = "test@example.com";
        String role = "CANDIDATE";

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(role);

        UserEntity userEntity = new UserEntity();
        userEntity.setRole(roleEntity);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);

        String result = userService.findRoleByMail(email);

        assertEquals(role, result);
    }
}
