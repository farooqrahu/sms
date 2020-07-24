package com.sms.spring.repository;

import com.sms.spring.domain.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
