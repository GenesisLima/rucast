package org.ntvru.rucast.security.service;

import java.util.HashSet;
import java.util.Set;

import org.ntvru.rucast.security.model.Role;
import org.ntvru.rucast.security.model.User;
import org.ntvru.rucast.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl  implements UserDetailsService{
	
	    @Autowired
	    private UserRepository userRepository;
	 
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByUsername(username);
		 
		 Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		 
		 
	        for (Role role : user.getRoles()){	        	
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//	            role.getPrivileges().stream()
//	            .map(p -> new SimpleGrantedAuthority(p.getName()))
//	            .forEach(grantedAuthorities::add);
	        }

	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
