package com.itjobapp.Security;

public interface PasswordEncoderService {
    String encodePassword(String password);

    boolean matches(String password, String password1);
}