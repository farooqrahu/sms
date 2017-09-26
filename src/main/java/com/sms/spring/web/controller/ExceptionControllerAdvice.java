package com.sms.spring.web.controller;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {

		ModelAndView mav = new ModelAndView("error");
		mav.addObject("name", e.getClass().getSimpleName());
		mav.addObject("message", e.getMessage());
		return mav;
	}

	@ExceptionHandler(NotReadablePropertyException.class)
	public ModelAndView exception(Exception exception) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("name", exception.getClass().getSimpleName());
		model.addObject("message", exception.getMessage());
		return model;
	}

	@ExceptionHandler(org.thymeleaf.exceptions.TemplateProcessingException.class)
	public ModelAndView thymLeafException(org.thymeleaf.exceptions.TemplateProcessingException exception) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("name", exception.getClass().getSimpleName());
		model.addObject("message", exception.getMessage());
		return model;
	}

	@ExceptionHandler(org.springframework.web.util.NestedServletException.class)
	public ModelAndView thymLeafException(org.springframework.web.util.NestedServletException exception) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("name", exception.getClass().getSimpleName());
		model.addObject("message", exception.getMessage());
		return model;
	}
	
	

}