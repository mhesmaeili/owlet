package com.owlet.api.security.service;


import com.owlet.api.security.dto.ChangePassword;
import com.owlet.api.security.dto.LoginRequest;
import com.owlet.api.security.dto.LoginResponse;
import jakarta.validation.Valid;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    Boolean changePassword(@Valid ChangePassword request);
}
