package com.owlet.api.controller;

import com.owlet.api.security.CurrentUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Home")
@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Owlet Service Running";
    }

    @GetMapping("/currentUser")
    public CurrentUser currentUser(
            @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return currentUser;
    }
}
