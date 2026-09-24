package com.owlet.api.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenRequest {
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9_-]{43}$", message = "ساختار refresh token معتبر نیست")
    private String refreshToken;
}
