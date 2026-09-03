package com.owlet.api.security.service;


import com.owlet.api.domain.idm.Account;
import com.owlet.api.repository.idm.AccountRepository;
import com.owlet.api.security.CurrentUserService;
import com.owlet.api.security.dto.ChangePassword;
import com.owlet.api.security.dto.LoginRequest;
import com.owlet.api.security.dto.LoginResponse;
import com.owlet.api.security.jwt.JwtService;
import com.owlet.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CurrentUserService currentUserService;


    @Override
    public LoginResponse login(LoginRequest request) {


        Account account = accountRepository
                .findByMobile(request.getUsername())
                .orElseThrow(() ->
                        new UnauthorizedException("Username or password incorrect")
                );


        if (Boolean.FALSE.equals(account.getActive())) {
            throw new UnauthorizedException("Account is inactive");
        }


        if (Boolean.TRUE.equals(account.getLocked())) {
            throw new UnauthorizedException("Account is locked");
        }


        if (!passwordEncoder.matches(
                request.getPassword(),
                account.getPasswordHash()
        )) {
            throw new UnauthorizedException("Username or password incorrect");
        }


        String token = jwtService.generateToken(account);


        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(7200L)
                .passwordMustChange(account.getPasswordMustChanged())
                .build();

    }

    @Override
    public Boolean changePassword(ChangePassword request) {

        Account account = accountRepository
                .findByMobile(currentUserService.getUsername())
                .orElseThrow(() ->
                        new UnauthorizedException("Username or password incorrect")
                );

        if (!passwordEncoder.matches(
                request.getOldPass(),
                account.getPasswordHash()
        )) {
            throw new UnauthorizedException("رمز قبلی شما صحیح نمی باشد");
        }

        account.setPasswordHash(passwordEncoder.encode(request.getNewPass()));
        account.setPasswordMustChanged(false);
        account.setPasswordChangedAt(OffsetDateTime.now());
        accountRepository.save(account);

        return true;
    }
}