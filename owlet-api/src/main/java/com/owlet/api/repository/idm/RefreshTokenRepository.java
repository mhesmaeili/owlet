package com.owlet.api.repository.idm;

import com.owlet.api.domain.idm.RefreshToken;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends BaseRepository<RefreshToken, UUID> {
    // Read only the session ID before acquiring its lock. Loading the token
    // entity before waiting for the lock could cache a stale usedAt value.
    @Query("select t.session.id from RefreshToken t where t.token = :hash")
    Optional<UUID> findSessionIdByHash(@Param("hash") String hash);

    @Query("select t from RefreshToken t where t.token = :hash")
    Optional<RefreshToken> findByHash(@Param("hash") String hash);

    @Modifying(flushAutomatically = true)
    @Query("update RefreshToken t set t.revoked = true, t.revokedAt = :now, " +
           "t.revokeReason = :reason where t.session.id = :sessionId and t.revoked = false")
    int revokeSessionTokens(@Param("sessionId") UUID sessionId,
                            @Param("now") OffsetDateTime now,
                            @Param("reason") String reason);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update RefreshToken t set t.revoked = true, t.revokedAt = :now, " +
           "t.revokeReason = :reason where t.account.id = :accountId and t.revoked = false")
    int revokeAccountTokens(@Param("accountId") UUID accountId,
                            @Param("now") OffsetDateTime now,
                            @Param("reason") String reason);
}
