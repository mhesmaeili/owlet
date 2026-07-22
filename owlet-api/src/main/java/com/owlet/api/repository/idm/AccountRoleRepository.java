package com.owlet.api.repository.idm;

import com.owlet.api.domain.idm.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRoleRepository extends JpaRepository<AccountRole, UUID> {
    void deleteByAccountId(UUID accountId);

}
