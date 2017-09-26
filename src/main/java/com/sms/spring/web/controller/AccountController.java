package com.sms.spring.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.sms.spring.business.AccountManager;
import com.sms.spring.viewmodel.AccountViewModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sms.spring.instrumentation.GlobalConstants;
import com.sms.spring.model.Role;
import com.sms.spring.model.User;

/**
 * @author Farooq-Rahu
 *
 */
@Controller
@Configuration
public class AccountController {
	private static final Logger logger = Logger.getLogger(AccountController.class);

	@Autowired
	private AccountManager accountManager;

	
	// Save for Sales Rep
	@RequestMapping(value = "/manageuser", method = RequestMethod.POST)
	public ModelAndView savesUser(@ModelAttribute("entity") AccountViewModel accountViewModel) {
		ModelAndView model = new ModelAndView();
		try {
			accountViewModel = accountManager.save(accountViewModel);
			logger.info("Save or or Update Completed.");
			accountViewModel.setUser(null);

			return accountManager.loadUserView(accountViewModel, accountViewModel.getModelAndView(),
					GlobalConstants.MANAGEUSER_HTML_PAGE);

		} catch (Exception e) {
			logger.error("Error: ", e);
			model.addObject(GlobalConstants.FAIL,
					GlobalConstants.getParametarizedMessage(GlobalConstants.SMS_USER_CREATE_UPDATE_FAIL,
							new Object[] { accountViewModel.getUser().getUsername() }));
			model.setViewName(GlobalConstants.MANAGEUSER_HTML_PAGE);
			model.addObject(accountViewModel);
			return model;
		}
	}

	// ajax get request for user
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(@ModelAttribute("userId") String userId) {
		AccountViewModel accountViewModel = new AccountViewModel();
		/*if (userId == null || userId.equals("")) {
			accountViewModel = accountManager.findAllUsers();
			// loading view
			return accountManager.loadUserView(accountViewModel, new ModelAndView(),
					GlobalConstants.MANAGEUSER_HTML_PAGE);
		}*/
		User user = accountManager.findUserById(Integer.parseInt(userId));
		accountViewModel.setRole(user.getRoles().iterator().next());
		accountViewModel.setUser(user);
		return accountManager.loadUserView(accountViewModel, new ModelAndView(), GlobalConstants.USER_MODALS_HTML_PAGE);
	}

	// Get Request and list for Users
//	@Secured ({"ROLE_SMS_OWNER", "ROLE_SALE_REP"})
	@RequestMapping(value = "/manageuser", method = RequestMethod.GET)
	public ModelAndView manageuser(@ModelAttribute("role") String rolename) {
		AccountViewModel accountViewModel = new AccountViewModel();
		if (rolename == null || rolename.equals("")) {
			accountViewModel = accountManager.findAllUsers();
			// loading view
			return accountManager.loadUserView(accountViewModel, new ModelAndView(),
					GlobalConstants.MANAGEUSER_HTML_PAGE);
		} else {
			return findByName(rolename);
		}
	}

	// Get request and list
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView userList(@ModelAttribute("role") String rolename) {
		AccountViewModel accountViewModel = new AccountViewModel();
		if (rolename == null || rolename.equals("")) {
			accountViewModel = accountManager.findAllUsers();
			// loading view
			return accountManager.loadUserView(accountViewModel, new ModelAndView(),
					GlobalConstants.MANAGEUSER_HTML_PAGE);
		} else {
			return findByName(rolename);
		}
	}

/*	// Get request and list
	@RequestMapping(value = "/savesUser", method = RequestMethod.GET)
	public ModelAndView savesUserList(@ModelAttribute("role") String rolename) {
		AccountViewModel accountViewModel = new AccountViewModel();
		if (rolename == null || rolename.equals("")) {
			accountViewModel = accountManager.findAllUsers();
			// loading view
			return accountManager.loadUserView(accountViewModel, new ModelAndView(),
					GlobalConstants.MANAGEUSER_HTML_PAGE);
		} else {
			return findByName(rolename);
		}
	}*/

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUser(@ModelAttribute("userIdDelete") String userId) {
		// Soft Delete User
		return accountManager.deleteUser(Integer.parseInt(userId));
	}

	public ModelAndView findByName(String rolename) {
		AccountViewModel accountViewModel = new AccountViewModel();
		Role role = accountManager.findByName(rolename);
		if (role != null) {
			accountViewModel = accountManager.findUserByRole(role);
			accountViewModel.setRole(role);
		}

		// loading view
		return accountManager.loadUserView(accountViewModel, new ModelAndView(), GlobalConstants.MANAGEUSER_HTML_PAGE);

	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public ModelAndView settings(@ModelAttribute("entity") AccountViewModel accountViewModel) {
		// loading view
		return accountManager.loadUserView(accountViewModel, new ModelAndView(),
				GlobalConstants.CHANGE_USER_PASSWORD_HTML_PAGE);
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public  @ResponseBody int changePassword(HttpServletRequest req,@ModelAttribute("oldPassword") String oldPassword,@ModelAttribute("newPassword") String newPassword) {
		// loading view
		return accountManager.changePassword(req,oldPassword,newPassword);
	}

	
	@RequestMapping(value = "/resetUserPassword", method = RequestMethod.POST)
	public ModelAndView resetUserPassword(@ModelAttribute("userIdReset") String userId) {
		// Reset User Password
		return accountManager.resetUserPassword(Integer.parseInt(userId));
	}
	
	
	
	
	
}
