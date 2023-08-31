package com.itjobapp.Service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class authService {

    public String getAuthUserName(Authentication authentication){
        return authentication.getName();
    }
}
