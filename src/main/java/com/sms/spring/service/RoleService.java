package com.sms.spring.service;

import com.sms.spring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.spring.model.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role findByName(String name){
		return roleRepository.findByName(name);
	}
	
	public Role saveRole(Role name){
		return roleRepository.save(name);
	}
}
