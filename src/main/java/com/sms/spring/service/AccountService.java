package com.sms.spring.service;

import java.util.List;

import com.sms.spring.repository.UserRepository;
import com.sms.spring.viewmodel.AccountViewModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.spring.model.Role;
import com.sms.spring.model.User;
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
		model.setUserList(userRepository.findAll());
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

	public User findUserById(int userId) {
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
