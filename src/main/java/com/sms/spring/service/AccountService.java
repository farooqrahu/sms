package com.sms.spring.service;

import com.sms.spring.domain.users.repositories.UserRepository;
import com.sms.spring.domain.users.Role;
import com.sms.spring.domain.users.User;
import com.sms.spring.viewmodel.AccountViewModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

;

@Service
public class AccountService {
	private static final Logger logger = Logger.getLogger(AccountService.class);

	@Autowired
	private UserRepository userRepository;

	public User save(User user) throws Exception {
		return userRepository.save(user);
	}

	public AccountViewModel findAll() {
		AccountViewModel model = new AccountViewModel();
		model.setUserList((List<User>) userRepository.findAll());
		return model;

	}

	@Transactional
	public User findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	@Transactional
	public List<User> findUserByRole(Role role) {
		List<User> userList = userRepository.findUserByRole(role);
		return userList;
	}

	public User findUserById(Long userId) {
		return userRepository.findUserById(userId);
	}

	public User deleteUser(User user) {
		// save works for both update and save
		return user = userRepository.save(user);
	}

	public boolean employeeIdExist(String empId) {
		User user = userRepository.employeeIdExist(empId);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean userNameExist(String userName) {
		User user = userRepository.userNameExist(userName);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	public List<User> findAllUsers() {
		List<User> userList = userRepository.findAllUsers();
		return userList;
	}



}
