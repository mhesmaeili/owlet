package com.owlet.api.security.service;


import com.owlet.api.security.dto.LoginRequest;
import com.owlet.api.security.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}
