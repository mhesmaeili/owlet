package com.owlet.api.repository.idm;

import com.owlet.api.domain.idm.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

}
