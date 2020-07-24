package com.sms.spring.domain.users;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Component
@Entity
@Table(name = "ROLES")
public class Role extends BaseEntityAudit {

	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> user;

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
