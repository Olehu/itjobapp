package com.itjobapp.Service;

import com.itjobapp.Security.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class authService {
    private final UserService userService;
    public String getAuthUserName(Authentication authentication){
        return authentication.getName();
    }

    public String getRoleName(Authentication authentication){
        return userService.findRoleByMail(authentication.getName());
    }
}
