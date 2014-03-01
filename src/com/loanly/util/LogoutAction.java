package com.loanly.util;



import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware , ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7834083122362526849L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public String execute() throws Exception{
		HttpSession session = request.getSession(false);
		if (session!=null){
			session.removeAttribute(LoginInterceptor.USER_SESSION_KEY);
            Map attibutes = ActionContext.getContext().getSession(); 
            attibutes.remove("username");
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if (LoginInterceptor.COOKIE_REMEMBERME_KEY.equals(cookie
						.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					return "login";
				}
			}
		}
		return "login";
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}