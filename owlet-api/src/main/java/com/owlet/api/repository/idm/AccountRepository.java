package com.owlet.api.repository.idm;

import com.owlet.api.domain.idm.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByUsernameAndDeletedFalse(String username);

    Optional<Account> findByIdAndDeletedFalse(UUID id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    Page<Account> findAllByDeletedFalse(Pageable pageable);

    Optional<Account> findByUsername(String username);


    @Query("""
        select distinct a
        from Account a
        left join fetch a.accountRoles ar
        left join fetch ar.role r
        left join fetch r.rolePermissions rp
        left join fetch rp.permission p
        where a.username = :username
        and a.deleted = false
    """)
    Optional<Account> findByUsernameForLogin(
            @Param("username") String username
    );
}
