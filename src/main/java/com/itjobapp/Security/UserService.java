package com.itjobapp.Security;

public interface UserService {
    void registerUser(String username, String password, String role);

    boolean authenticateUser(String email, String password);

    void updateEmail(String email);
}
