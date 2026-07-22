package com.owlet.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Base64;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        /*SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);

        String secret = Base64.getEncoder()
                .encodeToString(bytes);

        System.out.println(secret);*/
        return "Owlet Service Running";
    }
}
