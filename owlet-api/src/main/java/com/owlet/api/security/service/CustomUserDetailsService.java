package com.owlet.api.security.service;

import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.idm.AccountRole;
import com.owlet.api.domain.idm.RolePermission;
import com.owlet.api.repository.idm.AccountRepository;
import com.owlet.api.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Account account = accountRepository
                .findByUsernameForLogin(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(username));

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (AccountRole accountRole : account.getAccountRoles()) {

            if (!Boolean.TRUE.equals(accountRole.getActive()))
                continue;

            authorities.add(
                    new SimpleGrantedAuthority(
                            "ROLE_" +
                                    accountRole.getRole().getCode()));

            for (RolePermission rolePermission :
                    accountRole.getRole().getRolePermissions()) {

                if (!Boolean.TRUE.equals(rolePermission.getGranted()))
                    continue;

                authorities.add(
                        new SimpleGrantedAuthority(

                                rolePermission
                                        .getPermission()
                                        .getModule()

                                        +

                                        ":"

                                        +

                                        rolePermission
                                                .getPermission()
                                                .getCode()

                        ));

            }

        }

        return new CurrentUser(

                account.getId(),

                account.getUsername(),

                account.getPasswordHash(),

                account.getFirstName(),

                account.getLastName(),

                account.getActive(),

                account.getLocked(),

                authorities

        );

    }

}