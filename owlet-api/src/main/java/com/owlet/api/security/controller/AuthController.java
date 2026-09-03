package com.owlet.api.security.controller;


import com.owlet.api.dto.idm.RoleDto;
import com.owlet.api.security.CurrentUserService;
import com.owlet.api.security.dto.ChangePassword;
import com.owlet.api.security.dto.LoginRequest;
import com.owlet.api.security.dto.LoginResponse;
import com.owlet.api.security.service.AuthService;
import com.owlet.api.service.idm.AccountService;
import com.owlet.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AccountService accountService;
    private final CurrentUserService currentUserService;


    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {

        return authService.login(request);

    }

    @PutMapping("/changePassword")
    public ApiResponse<Boolean> changePass(
            @Valid @RequestBody ChangePassword request
    ) {

        return ApiResponse.success(authService.changePassword(request));

    }

    @GetMapping("/currentUserRoles")
    ApiResponse<List<RoleDto>> findActiveRolesByUsername() {
        return ApiResponse.success(accountService.findActiveRolesByUsername(currentUserService.getUsername()));
    }

}