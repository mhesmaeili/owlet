package com.owlet.api.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword {
    @NotBlank(message = "رمز عبور فعلی الزامی است")
    private String oldPass;

    @NotBlank(message = "رمز عبور جدید الزامی است")
    private String newPass;
}
