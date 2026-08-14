package com.owlet.api.repository.idm;

import com.owlet.api.domain.idm.Account;
import com.owlet.api.dto.idm.RoleDto;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository  extends BaseRepository<Account, UUID> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    Page<Account> findAllByDeletedFalse(Pageable pageable);

    Optional<Account> findByMobile(String mobile);

    @Query("""
        select distinct a
        from Account a
        left join fetch a.accountRoles ar
        left join fetch ar.role r
        left join fetch r.rolePermissions rp
        left join fetch rp.permission p
        where a.mobile = :mobile
        and a.deleted = false
    """)
    Optional<Account> findByMobileForLogin(
            @Param("mobile") String mobile
    );

    @Query("SELECT new com.owlet.api.dto.idm.RoleDto(r.id, r.code) " +
            "FROM Account a " +
            "JOIN a.accountRoles ar " +
            "JOIN ar.role r " +
            "WHERE a.mobile = :username AND ar.deleted = false")
    List<RoleDto> findActiveRoleDtosByUsername(@Param("username") String username);
}