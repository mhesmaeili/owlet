package com.owlet.api.repository.base;

import com.owlet.api.domain.base.ContactMessage;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactMessageRepository extends BaseRepository<ContactMessage, UUID> {
}