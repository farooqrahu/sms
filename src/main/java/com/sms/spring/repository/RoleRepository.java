package com.sms.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.spring.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
