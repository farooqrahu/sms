package com.sms.spring.business;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.sms.spring.instrumentation.Utility;
import com.sms.spring.service.RoleService;
import com.sms.spring.viewmodel.AccountViewModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.sms.spring.instrumentation.GlobalConstants;
import com.sms.spring.model.Role;
import com.sms.spring.model.User;
import com.sms.spring.service.AccountService;

@Component
public class AccountManager {
	private static final Logger logger = Logger.getLogger(AccountManager.class);

	@Autowired
	AccountService accountService;
	@Autowired
	private RoleService roleService;

	public AccountViewModel save(AccountViewModel accountViewModel) {
		ModelAndView model = new ModelAndView();
		if (accountViewModel.getUser().getRoles() != null) {
			logger.info("Save User Role:" + accountViewModel.getUser());
			User user = new User();
			// if existing user update fields.
			if (accountViewModel.getUser().getId() != null) {
				user = accountService.findUserById(accountViewModel.getUser().getId());
				user.setDesignation(accountViewModel.getUser().getDesignation());
				user.setEmail(accountViewModel.getUser().getEmail());
				user.setEnabled(accountViewModel.getUser().getEnabled());
				user.setName(accountViewModel.getUser().getName());
				user.setPhoneNo(accountViewModel.getUser().getPhoneNo());
				user.setRoles(accountViewModel.getUser().getRoles());
				accountViewModel.setUpdate(GlobalConstants.UPDATE);
			} else {
				// else new user save the object.
				accountViewModel.setSave(GlobalConstants.SAVE);
				user = accountViewModel.getUser();
				if (user.getPassword() != null && !user.getPassword().isEmpty()) {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					user.setPassword(encoder.encode(user.getPassword()));
				}
				// if duplicate Employee id
				if (user.getEmployeeId() != null && !user.getEmployeeId().isEmpty()) {
					if (accountService.employeeIdExist(user.getEmployeeId())) {
						model.addObject(GlobalConstants.FAIL,
								GlobalConstants.getParametarizedMessage(GlobalConstants.DUPLICATE_EMPLOYEE_ID,
										new Object[] { user.getUsername() == null ? "" : user.getUsername() }));
						accountViewModel = getUserAndRolesListOnSave(accountViewModel, model);
						return accountViewModel;
					}
				}
				// if duplicate user name
				if (accountService.userNameExist(user.getUsername())) {
					model.addObject(GlobalConstants.FAIL,
							GlobalConstants.getParametarizedMessage(GlobalConstants.DUPLICATE_USER_NAME,
									new Object[] { user.getUsername() == null ? "" : user.getUsername() }));
					accountViewModel = getUserAndRolesListOnSave(accountViewModel, model);
					return accountViewModel;
				}
			}
			try {
				// save user object
				user = accountService.save(user);
			} catch (Exception e) {
				logger.error("Error: ", e);
				model.addObject(GlobalConstants.FAIL,
						GlobalConstants.getParametarizedMessage(GlobalConstants.SMS_USER_CREATE_UPDATE_FAIL,
								new Object[] { user.getUsername() == null ? "" : user.getUsername() }));
				accountViewModel = getUserAndRolesListOnSave(accountViewModel, model);
				return accountViewModel;
			}
			// save success message..
			if (accountViewModel.getSave() != null && accountViewModel.getSave().equals(GlobalConstants.SAVE)) {
				model.addObject(GlobalConstants.SUCCESS,
						GlobalConstants.getParametarizedMessage(GlobalConstants.SMS_USER_CREATED_SUCCESS,
								new Object[] { user.getUsername() == null ? "" : user.getUsername() }));
			} else {
				// update success message..
				model.addObject(GlobalConstants.SUCCESS,
						GlobalConstants.getParametarizedMessage(GlobalConstants.SMS_USER_UPDATED_SUCCESS,
								new Object[] { user.getUsername() == null ? "" : user.getUsername() }));
			}
		}
		// finding user role
		accountViewModel = getUserAndRolesListOnSave(accountViewModel, model);
		return accountViewModel;
	}

	public AccountViewModel getUserAndRolesListOnSave(AccountViewModel accountViewModel, ModelAndView model) {
		accountViewModel = findAllUsers();
		accountViewModel.setModelAndView(model);
		return accountViewModel;
	}

	public AccountViewModel findUserByRole(Role role) {
		List<User> userList = accountService.findUserByRole(role);
		AccountViewModel accountViewModel = new AccountViewModel();
		accountViewModel.setUserList(userList);
		return accountViewModel;
	}

	public Role findByName(String rolename) {
		if (rolename == null || rolename.equals("")) {
			rolename = "ROLE_SALE_REP";
		}
		return roleService.findByName(rolename);
	}

	public User findUserById(int id) {
		return accountService.findUserById(id);
	}

	public ModelAndView deleteUser(int userId) {
		AccountViewModel accountViewModel = new AccountViewModel();
		ModelAndView model = new ModelAndView();
		User user = accountService.findUserById(userId);
		user.setEnabled(false);
		try {
			user = accountService.deleteUser(user);
			model.addObject(GlobalConstants.SUCCESS, GlobalConstants.getParametarizedMessage(
					GlobalConstants.SMS_USER_DELETE_SUCCESS, new Object[] { user.getUsername() }));
			model.setViewName(GlobalConstants.MANAGEUSER_HTML_PAGE);
			model.addObject(accountViewModel);
		} catch (Exception e) {
			model.addObject(GlobalConstants.FAIL, GlobalConstants.getParametarizedMessage(
					GlobalConstants.SMS_USER_DELETE_FAIL, new Object[] { user.getUsername() }));
			return loadUserView(accountViewModel, model, GlobalConstants.MANAGEUSER_HTML_PAGE);
		}
		accountViewModel = findAllUsers();
		// loading view
		return loadUserView(accountViewModel, model, GlobalConstants.MANAGEUSER_HTML_PAGE);
	}

	public AccountViewModel findAllUsers() {
		AccountViewModel accountViewModel = new AccountViewModel();
		List<User> userList = accountService.findAllUsers();
		if (userList.size() > 0) {
			accountViewModel.setUserList(userList);
			User u = new User();
			Set<Role> role = new HashSet<>();
			Iterator<User> it = userList.iterator();
			while (it.hasNext()) {
				u = it.next();
				role = u.getRoles();
				accountViewModel.setRole(role.iterator().next());
			}
			return accountViewModel;
		} else {
			accountViewModel = new AccountViewModel();
			Role role = new Role();
			role.setName("");
			role.setId(0);
			accountViewModel.setRole(role);
			return accountViewModel;
		}
	}

	public ModelAndView loadUserView(AccountViewModel accountViewModel, ModelAndView model, String view) {
		accountViewModel.setModelAndView(model);
		model.setViewName(view);
		return model.addObject("entity", accountViewModel);

	}

	public int changePassword(HttpServletRequest req,String oldPass,String newPass) {
		int status = 0;
		ModelAndView model = new ModelAndView();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = Utility.getLoggedInUser();
		boolean isAuthenticated = encoder.matches(oldPass, user.getPassword());
		if (isAuthenticated) {
			logger.info("Password Matched,changing password.");
			user = findUserById(user.getId());
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				// Encrypting new text password to hashed password
				user.setPassword(encoder.encode(newPass));
				user.setTempPassword(false);
				try {
					// save user object
					user = accountService.save(user);		
					status=0;
					req.getSession().setAttribute("tempPass", "false");
					model.addObject(GlobalConstants.SUCCESS,
							GlobalConstants.getMessage(GlobalConstants.SMS_USER_PASSWORD_CHANGE_SUCCESS));
				} catch (Exception e) {
					
					logger.error("Error: ", e);
					model.addObject(GlobalConstants.FAIL,
							GlobalConstants.getMessage(GlobalConstants.SMS_USER_PASSWORD_CHANGE_FAIL));
					return 1;
				}
			}
		} else {
			model.addObject(GlobalConstants.FAIL,
					GlobalConstants.getParametarizedMessage(GlobalConstants.SMS_USER_PASSWORD_MATCH_FAIL,
							new Object[] { user.getUsername() }));
			return 2;
		}
		return status;

	}

	public ModelAndView resetUserPassword(int userId) {		
		AccountViewModel accountViewModel = new AccountViewModel();
		ModelAndView model = new ModelAndView();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String tempPassword=Utility.generateRandomPassword();
		User user = findUserById(userId);	
			// Encrypting temp text password to hashed password
			user.setPassword(encoder.encode(tempPassword));
			user.setTempPassword(true);
			try {
				// save user object
				user = accountService.save(user);
				model.addObject(GlobalConstants.SUCCESS,
						GlobalConstants.getParametarizedMessage(GlobalConstants.SMS_USER_PASSWORD_RESET_SUCCESS,new Object[] { user.getUsername(),tempPassword}));
			} catch (Exception e) {
				logger.error("Error: ", e);
				model.addObject(GlobalConstants.FAIL,
						GlobalConstants.getMessage(GlobalConstants.SMS_USER_PASSWORD_RESET_FAIL));
				return model;
			}		
			accountViewModel = findAllUsers();
			// loading view
			return loadUserView(accountViewModel, model, GlobalConstants.MANAGEUSER_HTML_PAGE);
	}

}
