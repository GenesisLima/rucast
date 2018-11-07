package org.ntvru.rucast.security.service;

import org.ntvru.rucast.security.model.User;

public interface UserService {
	 void save(User user);

	    User findByUsername(String username);
}
