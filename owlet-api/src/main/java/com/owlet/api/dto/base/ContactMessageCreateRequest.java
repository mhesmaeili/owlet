package com.owlet.api.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactMessageCreateRequest {

    @NotBlank(message = "نام و نام خانوادگی الزامی است")
    private String fullName;

    @NotBlank(message = "شماره تماس الزامی است")
    @Pattern(regexp = "^09\\d{9}$", message = "شماره موبایل نامعتبر است. باید ۱۱ رقم بوده و با ۰۹ شروع شود")
    private String phoneNumber;

    @NotBlank(message = "متن پیام الزامی است")
    private String message;
}
