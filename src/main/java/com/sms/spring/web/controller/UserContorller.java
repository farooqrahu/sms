package com.sms.spring.web.controller;

import com.sms.spring.viewmodel.AccountViewModel;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Farooq-Rahu
 *
 */

@Controller
public class UserContorller {
	private static final Logger logger = Logger.getLogger(UserContorller.class);

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		logger.info("Loading accessDeniedPage.");
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView redirect404() {
		AccountViewModel accountviewModel = new AccountViewModel();
		accountviewModel.setModelAndView(new ModelAndView("404"));
		return accountviewModel.getModelAndView();

	}

	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public ModelAndView redirect500() {
		AccountViewModel accountviewModel = new AccountViewModel();
		accountviewModel.setModelAndView(new ModelAndView("500"));
		return accountviewModel.getModelAndView();

	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView redirectError() {
		AccountViewModel accountviewModel = new AccountViewModel();
		accountviewModel.setModelAndView(new ModelAndView("error"));
		return accountviewModel.getModelAndView();

	}

}
