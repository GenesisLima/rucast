package org.ntvru.rucast.security.repository;

import org.ntvru.rucast.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
