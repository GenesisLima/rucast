package org.ntvru.rucast.security.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="ru_role")
@Data
public class Role implements Serializable{

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	 @ManyToMany(mappedBy = "roles")
	private Set<User> users;
	
}
