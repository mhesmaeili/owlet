package com.owlet.api.repository.idm;

import com.owlet.api.domain.idm.AccountRole;
import com.owlet.api.repository.base.BaseRepository;

import java.util.UUID;

public interface AccountRoleRepository extends BaseRepository<AccountRole, UUID> {
    void deleteByAccountId(UUID accountId);
}
