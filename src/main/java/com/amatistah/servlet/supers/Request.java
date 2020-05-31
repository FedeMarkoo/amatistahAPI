package com.amatistah.servlet.supers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.amatistah.model.User;

public class Request extends HttpServletRequestWrapper {

	private boolean hasToRedirect;
	private User user;

	public void setUser(User user) {
		super.setAttribute("user", this.user = user);
	}
	
	public User getUser() {
		return user == null ? user = (User) super.getAttribute("user") : user;
	}

	public Request(HttpServletRequest request) {
		super(request);
	}

	public Integer getParameterAsInteger(String name) {
		return Integer.valueOf(super.getParameter(name));
	}

	public Long getParameterAsLong(String name) {
		return Long.valueOf(super.getParameter(name));
	}

	public boolean getParameterAsBoolean(String name) {
		return Boolean.valueOf(super.getParameter(name));
	}

	public String getParameterAsString(String name) {
		return super.getParameter(name);
	}

	public boolean existParameter(String name) {
		return super.getParameter(name) != null;
	}

	public boolean hasParameters() {
		return super.getParameterMap().size() == 0;
	}

	public boolean hasToRedirect() {
		return hasToRedirect;
	}

	public void setHasToRedirect(boolean hasToRedirect) {
		this.hasToRedirect = hasToRedirect;
	}

}
