package com.owlet.api.controller.profile;

import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.security.CurrentUserService;
import com.owlet.api.service.idm.AccountService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ProfileController")
@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController {

    private final AccountService accountService;
    private final CurrentUserService currentUserService;

    @GetMapping("/currentUserInfo")
    ApiResponse<AccountDto> findActiveRolesByUsername() {
        return ApiResponse.success(accountService.get(currentUserService.getCurrentUserId()));
    }
}
