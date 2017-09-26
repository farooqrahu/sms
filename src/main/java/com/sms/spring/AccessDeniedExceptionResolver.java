package com.sms.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class AccessDeniedExceptionResolver implements HandlerExceptionResolver, Ordered {
	private int order;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		ModelAndView model = new ModelAndView();
		if (exception instanceof AccessDeniedException) {
			return new ModelAndView("redirect:/accessDenied");
		} 
		return model;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return order;
	}
}