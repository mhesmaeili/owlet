package com.owlet.api.repository.idm;

import com.owlet.api.domain.idm.RefreshSession;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public interface RefreshSessionRepository extends JpaRepository<RefreshSession, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from RefreshSession s where s.id = :id")
    Optional<RefreshSession> findLocked(@Param("id") UUID id);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update RefreshSession s set s.revokedAt = :now, s.revokeReason = :reason " +
           "where s.account.id = :accountId and s.revokedAt is null")
    int revokeAll(@Param("accountId") UUID accountId,
                  @Param("now") OffsetDateTime now,
                  @Param("reason") String reason);
}
