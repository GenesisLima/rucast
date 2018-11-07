package org.ntvru.rucast.security.repository;

import org.ntvru.rucast.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUsername(String username);
}
