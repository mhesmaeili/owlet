package com.owlet.api.security.controller;

import com.owlet.api.dto.idm.RoleDto;
import com.owlet.api.security.CurrentUserService;
import com.owlet.api.security.dto.ChangePassword;
import com.owlet.api.security.dto.LoginRequest;
import com.owlet.api.security.dto.LoginResponse;
import com.owlet.api.security.dto.RefreshTokenRequest;
import com.owlet.api.security.service.AuthService;
import com.owlet.api.security.service.InvalidRefreshTokenException;
import com.owlet.api.security.service.RefreshTokenService;
import com.owlet.api.service.idm.AccountService;
import com.owlet.common.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AccountService accountService;
    private final CurrentUserService currentUserService;
    private final RefreshTokenService refreshTokenService;

    // ورود با نام کاربری و رمز عبور
    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response
    ) {
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");

        return authService.login(request);
    }

    // تغییر رمز عبور
    @PutMapping("/changePassword")
    public ApiResponse<Boolean> changePass(
            @Valid @RequestBody ChangePassword request
    ) {
        return ApiResponse.success(
                authService.changePassword(request)
        );
    }

    // نقش‌های کاربر جاری
    @GetMapping("/currentUserRoles")
    public ApiResponse<List<RoleDto>> findActiveRolesByUsername() {
        return ApiResponse.success(
                accountService.findActiveRolesByUsername(
                        currentUserService.getUsername()
                )
        );
    }

    // دریافت Access Token و Refresh Token جدید
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(
            @Valid @RequestBody RefreshTokenRequest body,
            HttpServletRequest request
    ) {
        LoginResponse result = refreshTokenService.refresh(
                body.getRefreshToken(),
                request.getRemoteAddr(),
                request.getHeader("User-Agent")
        );

        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore())
                .header(HttpHeaders.PRAGMA, "no-cache")
                .body(result);
    }

    // ابطال نشست مربوط به Refresh Token
    @PostMapping("/refresh/revoke")
    public ResponseEntity<Void> revoke(
            @Valid @RequestBody RefreshTokenRequest body
    ) {
        refreshTokenService.logout(body.getRefreshToken());

        return ResponseEntity.noContent()
                .cacheControl(CacheControl.noStore())
                .build();
    }

    // خطای اعتبار نشست
    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<RefreshFailure> invalidRefresh() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .cacheControl(CacheControl.noStore())
                .header(HttpHeaders.PRAGMA, "no-cache")
                .body(
                        new RefreshFailure(
                                "نشست معتبر نیست؛ دوباره وارد شوید."
                        )
                );
    }

    public record RefreshFailure(String message) {
    }
}